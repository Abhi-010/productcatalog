package dev.abhi.productcatalog.services;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.models.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    GenericProductDto getProductByID(Long id) ;
}
