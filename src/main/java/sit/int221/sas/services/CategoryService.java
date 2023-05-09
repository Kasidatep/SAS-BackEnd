package sit.int221.sas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sas.dto.CreateCategoryDto;
import sit.int221.sas.entities.Category;
import sit.int221.sas.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Integer id){
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category id " + id +" does not exist"));
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category addCategory(CreateCategoryDto category) {
        Category category1 = new Category();
        category1.setCategoryName(category.getCategoryName());
        return categoryRepository.saveAndFlush(category1);
    }
}
