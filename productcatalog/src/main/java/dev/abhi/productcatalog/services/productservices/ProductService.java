package dev.abhi.productcatalog.services.productservices;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    GenericProductDto getProductByID(UUID id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto) ;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(String uuid) throws NotFoundException;

    GenericProductDto updateProductById(GenericProductDto genericProductDto,long id) ;

    int getProductCountWith(String categoryName, long id);

    List<GenericProductDto> getProductByCategory(String categoryName) throws NotFoundException;
}
