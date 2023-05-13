package int221.MOON.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZonedDateTime;

public class ValidDateValidator implements ConstraintValidator<ValidDate, ZonedDateTime> {
    private ZonedDateTime closeDate;

    @Override
    public void initialize(ValidDate constraintAnnotation) {
    }

    // Set the closeDate
    public void setCloseDate(ZonedDateTime closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public boolean isValid(ZonedDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        if (value != closeDate) {
            return value.isAfter(currentDateTime) && (closeDate == null || value.isAfter(closeDate));
        }
        return value.isAfter(currentDateTime);
    }
}




