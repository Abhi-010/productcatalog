package dev.abhi.productcatalog.thirdpartyclients.FakeStore.category;

import dev.abhi.productcatalog.dtos.FakeStoreCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThirdPartyCategoryServiceClient {
    List<FakeStoreCategoryDto> getAllCategories() ;
}
