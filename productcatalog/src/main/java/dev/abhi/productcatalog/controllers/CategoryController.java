package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.requestBody.CategoryRequestBody;
import dev.abhi.productcatalog.services.categoryservices.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService ;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService ;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories() ;
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryRequestBody categoryRequestBody){
        return categoryService.createCategory(categoryRequestBody.getNewCategory());
    }

}
