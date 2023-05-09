package sit.int221.sas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.sas.dto.CreateAnnouncementDto;
import sit.int221.sas.dto.CreateAnnouncementReturnDto;
import sit.int221.sas.dto.CreateCategoryDto;
import sit.int221.sas.entities.Category;
import sit.int221.sas.services.CategoryService;

import java.util.List;

@CrossOrigin
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
    public Category CategoryDTO(@RequestBody CreateCategoryDto category) {
        return categoryservice.addCategory(category);
    }
}
