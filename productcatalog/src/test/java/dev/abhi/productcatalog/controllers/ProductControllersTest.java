package dev.abhi.productcatalog.controllers;


import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Product;
import dev.abhi.productcatalog.services.productservices.ProductService;
import dev.abhi.productcatalog.thirdpartyclients.FakeStore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProductControllersTest {

    @Autowired
    FakeStoreProductServiceClient fakeStoreProductServiceClient ;

    @MockBean
    //@Autowired
    ProductService productService ;

    @Test
    void testProductWithId() throws NotFoundException {
        GenericProductDto genericProductDto =
                productService.getProductByID(UUID.fromString("614d0c39-56d9-4338-a00b-9b922296b2fe")) ;

        when(productService.getProductByID(UUID.fromString("08a37622-30a7-45f4-aea6-f968dfa51e06"))).
                thenReturn(null) ;

        //assertEquals(genericProductDto.getCategory(),"Electronics","Categories are matching..");
        assertNull(genericProductDto);
    }

    @Test
    void shouldReturnTitle() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto() ;
        genericProductDto.setTitle("Mens Casual Slim Fit");

        when(productService.getProductByID(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a")))
                .thenReturn(genericProductDto) ;

        GenericProductDto genericProductDto1 =
                productService.getProductByID(UUID.fromString("e7500b3f-0861-47ce-9503-e17138d8934a"));

        assertEquals("Mens Casual Slim Fit", genericProductDto1.getTitle());
    }
}
