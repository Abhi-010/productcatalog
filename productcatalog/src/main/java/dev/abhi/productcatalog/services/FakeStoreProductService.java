package dev.abhi.productcatalog.services;

import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Product;
import dev.abhi.productcatalog.thirdpartyclients.productservice.ThirdPartyProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
//@Qualifier("fakeProductService")
@Primary
public class FakeStoreProductService implements ProductService{


    private final ThirdPartyProductServiceClient thirdPartyProductServiceClient ;

    public FakeStoreProductService(ThirdPartyProductServiceClient thirdPartyProductServiceClient){
        this.thirdPartyProductServiceClient = thirdPartyProductServiceClient;
    }

    @Override
    public GenericProductDto getProductByID(Long id) throws NotFoundException {
        return convertToGenericProductDto(thirdPartyProductServiceClient.getProductByID(id)) ;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

       return convertToGenericProductDto(thirdPartyProductServiceClient.createProduct(genericProductDto));

    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = thirdPartyProductServiceClient.getAllProducts();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>() ;

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
                genericProductDtoList.add(convertToGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtoList ;
    }

    @Override
    public GenericProductDto deleteProductById(long id) {
       return convertToGenericProductDto(thirdPartyProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, long id) {
       return convertToGenericProductDto(thirdPartyProductServiceClient.updateProductById(genericProductDto,id));
    }

    @Override
    public int getProductCountWith(String categoryName, long id) {
        List<GenericProductDto> genericProductDtoList = getAllProducts();
        genericProductDtoList.removeIf(genericProductDto -> !genericProductDto.getCategory().equals(categoryName));
        genericProductDtoList.removeIf(genericProductDto -> genericProductDto.getId() < 9 );
        return genericProductDtoList.size();
    }


    GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){

        GenericProductDto genericProductDto = new GenericProductDto() ;

        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());

        return genericProductDto ;
    }
}
