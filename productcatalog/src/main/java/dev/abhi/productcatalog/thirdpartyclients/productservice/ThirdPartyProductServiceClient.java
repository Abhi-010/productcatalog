package dev.abhi.productcatalog.thirdpartyclients.productservice;

import dev.abhi.productcatalog.dtos.FakeStoreProductDto;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto getProductByID(Long id) throws NotFoundException;

    FakeStoreProductDto createProduct(GenericProductDto genericProductDto) ;

    List<FakeStoreProductDto> getAllProducts();

    FakeStoreProductDto deleteProductById(long id);

    FakeStoreProductDto updateProductById(GenericProductDto genericProductDto,long id);
}