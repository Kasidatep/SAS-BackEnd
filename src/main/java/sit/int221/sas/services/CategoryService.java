package sit.int221.sas.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sas.dto.CreateCategoryDto;
import sit.int221.sas.entities.Category;
import sit.int221.sas.exceptions.EntityValidator;
import sit.int221.sas.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Integer id){
        if (id != null) {
            return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category id " + id +" does not exist"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category Id cannot be null");
        }
      }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    public Category addCategory(CreateCategoryDto category) {
        Category category1 = new Category();
        category1.setCategoryName(category.getCategoryName());
        EntityValidator.validateEntity(category1);
        if(!categoryRepository.existsCategoryByCategoryName(category1.getCategoryName())){
            return categoryRepository.saveAndFlush(category1);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name must be unique");
        }

    }

    public boolean isExist(Integer id){
        return categoryRepository.existsById(id);
    }
}
