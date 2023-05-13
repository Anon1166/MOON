package int221.MOON.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {

    String message() default "Invalid date format or date condition not met";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


