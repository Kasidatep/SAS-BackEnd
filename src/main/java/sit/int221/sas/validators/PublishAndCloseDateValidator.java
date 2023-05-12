package sit.int221.sas.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.sas.dto.CreateAnnouncementDto;

import java.time.ZonedDateTime;

public class PublishAndCloseDateValidator implements ConstraintValidator<PublishAndCloseDate, CreateAnnouncementDto> {
    @Override
    public boolean isValid(CreateAnnouncementDto dto, ConstraintValidatorContext context) {
        ZonedDateTime publishDate = dto.getPublishDate();
        ZonedDateTime closeDate = dto.getCloseDate();

        if (publishDate != null && closeDate != null) {
            return publishDate.isBefore(closeDate);
        }

        return true;
    }
}
