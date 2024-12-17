package dev.abhi.productcatalog.services.categoryservices;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<CategoryDto> getAllCategories() ;
    CategoryDto createCategory(String newCategory);
    List<Product> getAllProductsByCategoryName(String categoryName) throws NotFoundException;
}
