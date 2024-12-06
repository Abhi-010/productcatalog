package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.services.categoryservices.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    private final CategoryService categoryService ;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService ;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories() ;
    }
}
