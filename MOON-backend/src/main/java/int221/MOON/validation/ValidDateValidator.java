package int221.MOON.validation;

import int221.MOON.Dto.InputAnnouncesDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.time.ZonedDateTime;


public class ValidDateValidator implements ConstraintValidator<ValidDate, InputAnnouncesDTO> {


    @Override
    public void initialize(ValidDate constraintAnnotation) {
    }


    @Override
    public boolean isValid(InputAnnouncesDTO i, ConstraintValidatorContext context) {
        if (i.getPublishDate() == null || i.getCloseDate() == null) {
            return true;
        }

        ZonedDateTime currentDate = ZonedDateTime.now();

        context.disableDefaultConstraintViolation();

        if (i.getPublishDate().isBefore(currentDate)) {
            context.buildConstraintViolationWithTemplate("Invalid publishDate")
                    .addPropertyNode("publishDate")
                    .addConstraintViolation();
            return false;
        } else if (i.getCloseDate().isBefore(currentDate)) {
            context.buildConstraintViolationWithTemplate("Invalid closeDate")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        } else if (i.getPublishDate().isAfter(i.getCloseDate()) || i.getPublishDate().equals(i.getCloseDate())) {
            context.buildConstraintViolationWithTemplate("Invalid publishDate")
                    .addPropertyNode("publishDate")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid closeDate")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }


}




