package dev.abhi.productcatalog.thirdpartyclients.FakeStore;

import dev.abhi.productcatalog.dtos.CategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreCategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreCategoryServiceClient implements ThirdPartyCategoryServiceClient{

    private final RestTemplateBuilder restTemplateBuilder ;

    @Value("${fakeStore.url}")
    private String baseRequestUrl;

    public FakeStoreCategoryServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<FakeStoreCategoryDto> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build() ;

        ResponseEntity<String[]> responseEntity =
                restTemplate.getForEntity(baseRequestUrl + "categories",String[].class);

        List<FakeStoreCategoryDto> fakeStoreCategoryDtoList = new ArrayList<>();

        String[] categories = responseEntity.getBody() ;

        for(var obj : categories){
            fakeStoreCategoryDtoList.add(new FakeStoreCategoryDto(obj));
        }
        return fakeStoreCategoryDtoList ;
    }
}
