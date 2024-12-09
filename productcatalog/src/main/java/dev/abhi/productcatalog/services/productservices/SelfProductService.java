package dev.abhi.productcatalog.services.productservices;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Qualifier("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    @Override
    public GenericProductDto getProductByID(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public GenericProductDto deleteProductById(long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto,long id) {
        return null;
    }

    @Override
    public int getProductCountWith(String categoryName, long id) {
        return 0;
    }

    @Override
    public List<GenericProductDto> getProductByCategory(String categoryName) {
        return List.of();
    }
}
