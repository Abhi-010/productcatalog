package dev.abhi.productcatalog.services;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
//@Qualifier("selfProductService")
public class SelfProductService implements ProductService {

    @Override
    public GenericProductDto getProductByID(Long id) {
        return null;
    }
}
