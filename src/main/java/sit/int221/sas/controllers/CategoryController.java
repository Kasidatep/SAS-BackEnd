package sit.int221.sas.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sit.int221.sas.dto.CreateAnnouncementDto;
import sit.int221.sas.dto.CreateAnnouncementReturnDto;
import sit.int221.sas.dto.CreateCategoryDto;
import sit.int221.sas.entities.Category;
import sit.int221.sas.services.CategoryService;

import java.util.List;

@CrossOrigin(origins = "http://intproj22.sit.kmutt.ac.th")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryservice;

    @GetMapping("")
    public List<Category> getAllCategory(){
        return categoryservice.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryservice.getCategoryById(id);
    }

    @PostMapping("")
    public Category CategoryDTO(
            @Valid  @RequestBody CreateCategoryDto category,
            BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
        }else{
           return categoryservice.addCategory(category);
        }
    }
}
