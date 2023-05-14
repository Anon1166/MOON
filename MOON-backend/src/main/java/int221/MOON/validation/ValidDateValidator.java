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
        System.out.println(i.getCloseDate());
        System.out.println(i.getPublishDate());

        if (i.getCloseDate() == null && i.getPublishDate() == null) {
            System.out.println("nullDate");
            return true;
        }

        ZonedDateTime currentDate = ZonedDateTime.now();
        System.out.println(currentDate);

        context.disableDefaultConstraintViolation();

        if (i.getPublishDate() != null && i.getPublishDate().isBefore(currentDate)) {
            System.out.println("getPublishDateisBeforecurrentDate");
            context.buildConstraintViolationWithTemplate("must be a date in the present or in the future")
                    .addPropertyNode("publishDate")
                    .addConstraintViolation();
            return false;
        } else if (i.getCloseDate() != null && i.getCloseDate().isBefore(currentDate)) {
            System.out.println("getCloseDateisBeforecurrentDate");
            context.buildConstraintViolationWithTemplate("must be a future date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        } else if (i.getPublishDate() != null && i.getCloseDate() != null && (i.getPublishDate().isAfter(i.getCloseDate()) || i.getPublishDate().equals(i.getCloseDate()))) {
            System.out.println("getPublishDateisAftergetCloseDate");
            context.buildConstraintViolationWithTemplate("must be lower than close date")
                    .addPropertyNode("publishDate")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("must be later than publish date")
                    .addPropertyNode("closeDate")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }


}




