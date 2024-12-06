package dev.abhi.productcatalog.thirdpartyclients.FakeStore;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreCategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThirdPartyCategoryServiceClient {
    List<FakeStoreCategoryDto> getAllCategories() ;
}
