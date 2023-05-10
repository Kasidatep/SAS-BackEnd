package sit.int221.sas.exceptions;

import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityValidator {
    public static <T> void validateEntity(T entity) throws ConstraintViolationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<T> violation : violations) {
                errors.add(violation.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(", ", errors));
        }
    }
}
