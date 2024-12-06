package dev.abhi.productcatalog.services.productservices;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductByID(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto) ;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(long id);

    GenericProductDto updateProductById(GenericProductDto genericProductDto,long id) ;

    int getProductCountWith(String categoryName, long id);

    List<GenericProductDto> getProductByCategory(String categoryName);
}
