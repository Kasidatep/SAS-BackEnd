package sit.int221.sas.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sit.int221.sas.repositories.CategoryRepository;

@Component
public class CategoryIdValidator implements ConstraintValidator<CategoryIdExists, Integer> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(Integer categoryId, ConstraintValidatorContext context) {
        if (categoryId == null) {
            return true;
        }
        return categoryRepository.existsById(categoryId);
    }

}