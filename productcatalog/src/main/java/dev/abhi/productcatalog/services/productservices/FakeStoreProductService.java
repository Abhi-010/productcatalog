package dev.abhi.productcatalog.services.productservices;

import dev.abhi.productcatalog.dtos.FakeStoreCategoryDto;
import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.thirdpartyclients.FakeStore.ThirdPartyProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
//@Qualifier("fakeProductService")
//@Primary
public class FakeStoreProductService implements ProductService{

    private final ThirdPartyProductServiceClient thirdPartyProductServiceClient ;

    public FakeStoreProductService(ThirdPartyProductServiceClient thirdPartyProductServiceClient){
        this.thirdPartyProductServiceClient = thirdPartyProductServiceClient;
    }

    public GenericProductDto getProductByID(Long id) throws NotFoundException {
        return convertToGenericProductDto(thirdPartyProductServiceClient.getProductByID(id)) ;
    }

    @Override
    public GenericProductDto getProductByID(UUID id) throws NotFoundException {
        return null;
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
    public GenericProductDto deleteProductById(String uuid) {
        long id = Long.parseLong(uuid) ;
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

       // genericProductDtoList.removeIf(genericProductDto -> genericProductDto.getId() < 9 );
        return genericProductDtoList.size();
    }

    @Override
    public List<GenericProductDto> getProductByCategory(String categoryName) {
        List<FakeStoreProductDto> fakeStoreProductDtoList = thirdPartyProductServiceClient.getProductByCategory(categoryName);

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for(var obj : fakeStoreProductDtoList){
            genericProductDtoList.add(convertToGenericProductDto(obj));
        }
        return genericProductDtoList;
    }


    GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){

        GenericProductDto genericProductDto = new GenericProductDto() ;

        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setId(fakeStoreProductDto.getId()+ " ");
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());

        return genericProductDto ;
    }
}
