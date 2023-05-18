package sit.int221.sas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
//        List<ValidationError> errors = new ArrayList<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            if (error instanceof FieldError) {
//                String field = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.add(new ValidationError(field, errorMessage));
//            } else {
//                String errorMessage = error.getDefaultMessage();
//                errors.add(new ValidationError("", errorMessage));
//            }
//        });
//
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase() ,"", errors);
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String field = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ValidationError(field, errorMessage));
        });
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), webRequest.getDescription(false).substring(4), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private List<ValidationError> detail;

    public ErrorResponse(int status, String message, String path, List<ValidationError> detail) {
        this.status = status;
        this.message = message;
        this.path = String.valueOf(path);
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ValidationError> getDetail() {
        return detail;
    }

    public void setDetail(List<ValidationError> detail) {
        this.detail = detail;
    }
}

class ValidationError {
    private String field;
    private String errorMessage;

    public ValidationError(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



}