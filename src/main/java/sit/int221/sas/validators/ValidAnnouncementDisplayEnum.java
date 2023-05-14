package sit.int221.sas.validators;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import sit.int221.sas.utils.AnnouncementDisplayEnum;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAnnouncementDisplayEnumValidator.class)
public @interface ValidAnnouncementDisplayEnum {
    String message() default "announcementDisplay must be 'Y' or 'N";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

