package dev.abhi.productcatalog.services.categoryservices;

import dev.abhi.productcatalog.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<CategoryDto> getAllCategories() ;
}
