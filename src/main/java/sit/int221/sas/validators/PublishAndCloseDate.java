package sit.int221.sas.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = PublishAndCloseDateValidator.class)
@Target({ElementType.TYPE_USE, FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
public @interface PublishAndCloseDate {
    String message() default "publishDate must be before closeDate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
