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

@Service
//@Qualifier("fakeProductService")
@Primary
public class FakeStoreProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder ;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder ;
    }

    @Override
    public GenericProductDto getProductByID(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build() ;

        //    @Value("${request.url}")
        //    private String url;

        String requestUrl = "https://fakestoreapi.com/products/{id}";

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(requestUrl, FakeStoreProductDto.class,id) ;

        FakeStoreProductDto fakeStoreProductDto = response.getBody() ;

        GenericProductDto genericProductDto = new GenericProductDto() ;

        //assert fakeStoreProductDto != null;
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setImageUrl(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());


        return genericProductDto ;

    }
}
