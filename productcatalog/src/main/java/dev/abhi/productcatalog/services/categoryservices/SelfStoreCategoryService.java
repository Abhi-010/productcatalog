package dev.abhi.productcatalog.services.categoryservices;

import dev.abhi.productcatalog.Repository.CategoryRepository;
import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Category;
import dev.abhi.productcatalog.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfStoreCategoryService implements CategoryService{

    private final CategoryRepository categoryRepository ;
    public SelfStoreCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository ;
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDto createCategory(String newCategory) {
        Category category = new Category() ;
        category.setName(newCategory);

        Category savedCategory = categoryRepository.save(category);

        CategoryDto categoryDto = new CategoryDto() ;
        categoryDto.setName(savedCategory.getName());

        return categoryDto;
    }

    @Override
    public List<Product> getAllProductsByCategoryName(String categoryName) throws NotFoundException {
        Optional<Category> optionalCategory =  categoryRepository.findCategoryByName(categoryName) ;
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("Category Name not Found");
        }
        return optionalCategory.get().getProductList();
    }
}
