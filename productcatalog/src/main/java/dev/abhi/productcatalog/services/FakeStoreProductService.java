package dev.abhi.productcatalog.services;

import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
//@Qualifier("fakeProductService")
@Primary
public class FakeStoreProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder ;

    @Value("${fakeStore.url}")
    private String baseRequestUrl;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder ;
    }

    @Override
    public GenericProductDto getProductByID(Long id) {

        //    @Value("${request.url}")
        //    private String url;

        RestTemplate restTemplate = restTemplateBuilder.build() ;
        String requestUrl = baseRequestUrl + "{id}";

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(requestUrl, FakeStoreProductDto.class,id) ;

        FakeStoreProductDto fakeStoreProductDto = response.getBody() ;

        GenericProductDto genericProductDto = new GenericProductDto() ;

        //assert fakeStoreProductDto != null;
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());

        return genericProductDto ;

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build() ;
        ResponseEntity<GenericProductDto> genericProductDtoResponseEntity =
                restTemplate.postForEntity(baseRequestUrl,genericProductDto, GenericProductDto.class) ;

        return  genericProductDtoResponseEntity.getBody();

    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        String requestUrl = "https://fakestoreapi.com/products" ;
        RestTemplate restTemplate = restTemplateBuilder.build() ;

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(requestUrl,FakeStoreProductDto[].class) ;

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){

            GenericProductDto genericProductDto = new GenericProductDto() ;
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            genericProductDto.setId(fakeStoreProductDto.getId());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());

            genericProductDtoList.add(genericProductDto) ;

        }
        return genericProductDtoList ;
    }
}
