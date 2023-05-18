package sit.int221.sas.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.sas.dto.CreateAnnouncementDto;


public class CloseDateValidator implements ConstraintValidator<ValidDate, CreateAnnouncementDto> {
    @Override
    public boolean isValid(CreateAnnouncementDto dto, ConstraintValidatorContext context) {
        if (dto.getCloseDate() == null || dto.getPublishDate() == null) {
            return true; // Skip validation if either date is null
        }
//        return dto.getCloseDate().isAfter(dto.getPublishDate());
        boolean isValid = dto.getCloseDate().isAfter(dto.getPublishDate());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("closeDate").addConstraintViolation();
        }
        return isValid;
    }
}