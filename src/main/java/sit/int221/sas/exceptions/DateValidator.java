package sit.int221.sas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

public class DateValidator {

    public static void isCorrect(ZonedDateTime publishDate, ZonedDateTime closeDate) {
        if(publishDate != null && closeDate != null && publishDate.isAfter(closeDate)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The publish date must be before the closing date.");
        }
    }
}
