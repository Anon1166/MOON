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


        if (i.getCloseDate() == null && i.getPublishDate() == null) {
            return true;
        }

        ZonedDateTime currentDate = ZonedDateTime.now();


        context.disableDefaultConstraintViolation();

        if (i.getPublishDate() != null && i.getPublishDate().isBefore(currentDate)) {
            context.buildConstraintViolationWithTemplate("must be a date in the present or in the future")
                    .addPropertyNode("publishDate")
                    .addConstraintViolation();
            return false;
        } else if (i.getCloseDate() != null && i.getCloseDate().isBefore(currentDate)) {
            context.buildConstraintViolationWithTemplate("must be a future date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        } else if (i.getPublishDate() != null && i.getCloseDate() != null && (i.getPublishDate().isAfter(i.getCloseDate()) || i.getPublishDate().equals(i.getCloseDate()))) {
            context.buildConstraintViolationWithTemplate("must be later than publish date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }


}




