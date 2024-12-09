package dev.abhi.productcatalog.services.categoryservices;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreCategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import dev.abhi.productcatalog.thirdpartyclients.FakeStore.ThirdPartyCategoryServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService{

    private final ThirdPartyCategoryServiceClient thirdPartyCategoryServiceClient;

    public FakeStoreCategoryService(ThirdPartyCategoryServiceClient thirdPartyCategoryServiceClient){
        this.thirdPartyCategoryServiceClient = thirdPartyCategoryServiceClient ;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<FakeStoreCategoryDto> fakeStoreCategoryDtoList = thirdPartyCategoryServiceClient.getAllCategories() ;

        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(var obj : fakeStoreCategoryDtoList){

            CategoryDto categoryDto = new CategoryDto() ;
            categoryDto.setName(obj.getName());
            categoryDtoList.add(categoryDto) ;
        }
        return categoryDtoList ;
    }

    @Override
    public CategoryDto createCategory(String newCategory) {
        return null;
    }
}
