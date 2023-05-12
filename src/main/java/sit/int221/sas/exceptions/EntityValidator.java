package sit.int221.sas.exceptions;

import jakarta.validation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter @Getter @AllArgsConstructor @ToString
public class EntityValidator {

    public static <T> Set<ConstraintViolation<T>> validateEntity(T entity) throws ConstraintViolationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            List<ValidationError> errors = new ArrayList<>();
            for (ConstraintViolation<T> violation : violations) {
                String field = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.add(new ValidationError(field, message));
            }
        return violations;
        }
        return null;
    }

    public static class ValidationError {
        private final String field;
        private final String errorMessage;

        public ValidationError(String field, String errorMessage) {
            this.field = field;
            this.errorMessage = errorMessage;
        }

        public String getField() {
            return field;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

}
