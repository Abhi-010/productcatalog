package dev.abhi.productcatalog.services.productservices;


import dev.abhi.productcatalog.Repository.ProductRepository;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Category;
import dev.abhi.productcatalog.models.Currency;
import dev.abhi.productcatalog.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelfProductServiceTest {

    @InjectMocks
    SelfProductService productService ;

    @Mock
    private ProductRepository productRepository ;

    @Test
    void testGetByProductById() throws NotFoundException {
        // Arrange :
        Product product = new Product();
        product.setId(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a"));
        product.setTitle("Mens Casual Slim Fit");
        product.setCategory(new Category());
        product.setPrice(67.99);
        product.setImage("Image1");
        product.setDescription("Description");
        product.setCurrency(Currency.INR);

        when(productRepository.findById(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a")))
                .thenReturn(Optional.of(product));

        // Act :
        GenericProductDto fetchedProduct =
                productService.getProductByID(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a")) ;

        // Assert :
        assertNotNull(fetchedProduct);
        assertEquals(fetchedProduct.getImage(),product.getImage());
    }
    @Test
    void throwNotFoundExceptionWhenProductNotAvailableInGetProductById() throws NotFoundException {
        // Arrange

        when(productRepository.findById(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a")))
                .thenReturn(null);

        // Act

        //GenericProductDto genericProductDto = productService.getProductByID(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934z"));

        // Assert

        NotFoundException exception =
                assertThrows(NotFoundException.class,()-> productService.getProductByID(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a")));

        assertEquals("Product is not available", exception.getMessage());
    }
}
