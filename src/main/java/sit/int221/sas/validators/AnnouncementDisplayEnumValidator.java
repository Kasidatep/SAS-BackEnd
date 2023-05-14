package sit.int221.sas.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.sas.utils.AnnouncementDisplayEnum;

public class AnnouncementDisplayEnumValidator implements ConstraintValidator<ValidAnnouncementDisplayEnum, AnnouncementDisplayEnum> {
    @Override
    public void initialize(ValidAnnouncementDisplayEnum constraintAnnotation) {
    }

    @Override
    public boolean isValid(AnnouncementDisplayEnum value, ConstraintValidatorContext context) {
        return value == AnnouncementDisplayEnum.Y || value == AnnouncementDisplayEnum.N;
    }
}
