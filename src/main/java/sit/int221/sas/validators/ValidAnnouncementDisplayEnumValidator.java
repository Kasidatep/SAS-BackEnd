package sit.int221.sas.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sit.int221.sas.utils.AnnouncementDisplayEnum;

public class ValidAnnouncementDisplayEnumValidator implements ConstraintValidator<ValidAnnouncementDisplayEnum, AnnouncementDisplayEnum> {
    @Override
    public void initialize(ValidAnnouncementDisplayEnum constraintAnnotation) {
    }

    @Override
    public boolean isValid(AnnouncementDisplayEnum value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // null value not allowed
        }else{
            if("Y".equals(value) || "N".equals(value)){
                return false;
            }else{
                return true;
            }
        }

       // return value == AnnouncementDisplayEnum.Y || value == AnnouncementDisplayEnum.N;
    }
}
