package sit.int221.sas.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryIdValidator.class)
public @interface CategoryIdExists {
    String message() default "does not exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
