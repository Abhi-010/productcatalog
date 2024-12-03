package dev.abhi.productcatalog.services;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductByID(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto) ;

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(long id);

    GenericProductDto updateProductById(GenericProductDto genericProductDto,long id) ;
}
