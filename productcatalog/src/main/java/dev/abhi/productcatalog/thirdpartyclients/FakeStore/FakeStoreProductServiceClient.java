package dev.abhi.productcatalog.thirdpartyclients.FakeStore;

import dev.abhi.productcatalog.dtos.FakeStoreCategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Primary
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {

    private final RestTemplateBuilder restTemplateBuilder ;

    @Value("${fakeStore.url}")
    private String baseRequestUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder ;
    }

    @Override
    public FakeStoreProductDto getProductByID(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build() ;
        String requestUrl = baseRequestUrl + "{id}";

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(requestUrl, FakeStoreProductDto.class,id) ;

        FakeStoreProductDto fakeStoreProductDto = response.getBody() ;

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id " + id + " doesn't exist");
        }
        return fakeStoreProductDto ;
    }

    @Override
    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build() ;
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.postForEntity(baseRequestUrl,genericProductDto, FakeStoreProductDto.class) ;

        return  fakeStoreProductDtoResponseEntity.getBody();
    }

    @Override
    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build() ;

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(baseRequestUrl,FakeStoreProductDto[].class) ;

        List<FakeStoreProductDto> fakeStoreProductDtos = new ArrayList<>(Arrays.asList(response.getBody()));
        return fakeStoreProductDtos ;
    }

    @Override
    public FakeStoreProductDto deleteProductById(long id) {
        String requestUrl = baseRequestUrl + "{id}";

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        RequestCallback requestCallback =
                restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(requestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    @Override
    public FakeStoreProductDto updateProductById(GenericProductDto genericProductDto, long id) {
        String requestUrl = baseRequestUrl + "{id}";

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(genericProductDto,FakeStoreProductDto.class);

        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);


        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(requestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    @Override
    public List<FakeStoreProductDto> getProductByCategory(String categoryName) {
        String requestUrl = baseRequestUrl + "category/" + categoryName;

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        HttpEntity<FakeStoreProductDto> httpEntity = new HttpEntity<>(null) ;

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.exchange(requestUrl,HttpMethod.GET,httpEntity,FakeStoreProductDto[].class);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }


}
