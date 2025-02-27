package dev.abhi.productcatalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Product;
import dev.abhi.productcatalog.services.productservices.ProductService;
import dev.abhi.productcatalog.services.productservices.SelfProductService;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private SelfProductService productService ;

    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        // arrange :
        when(productService.getAllProducts()).thenReturn(new ArrayList<>()) ;
        // Act :
        mockMvc.perform(
                get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]")
        ) ;
    }

    @Test
    void returnListOfProductsWhenProductExists() throws Exception {
        //Arrange :

        List<GenericProductDto> listOfProducts = new ArrayList<>();

        GenericProductDto genericProductDto = new GenericProductDto() ;
        genericProductDto.setId("20da49c5-ddd8-4a8d-ba50-17a3fa9f5a12");
        genericProductDto.setImage("genericProductDto_image1");
        genericProductDto.setDescription("This is a full pant for Boys");
        genericProductDto.setCategory("Men's clothing");
        genericProductDto.setInventoryCount(100);
        genericProductDto.setPrice(10001.0);
        genericProductDto.setCurrency("INR");
        genericProductDto.setTitle("Men's jeans and pants");


        GenericProductDto genericProductDto1 = new GenericProductDto() ;
        genericProductDto1.setId("20da49c5-ddd8-4a8d-ba50-17a3fa9f5a12");
        genericProductDto1.setImage("genericProductDto_image2");
        genericProductDto1.setDescription("This is a full pant for Girls");
        genericProductDto1.setCategory("Owmen's clothing");
        genericProductDto1.setInventoryCount(1001);
        genericProductDto1.setPrice(10001.0);
        genericProductDto1.setCurrency("INR");
        genericProductDto1.setTitle("Owmen's jeans and pants");

        when(productService.getAllProducts()).thenReturn(listOfProducts);

        //Act :

        mockMvc.perform(
                get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(listOfProducts)))
                .andExpect(content().contentType(MediaType. APPLICATION_JSON))
        ;
    }

    @Test
    void createProductShouldCreateNewProduct() throws Exception {
        GenericProductDto productToCreate = new GenericProductDto() ;
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImage("some image");
        productToCreate.setCategory("mobile phones");
        productToCreate.setDescription("Best iPhone Ever");

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setId("61c84078-da0a-4b8e-9f9f-59e0b43a6c05");
        expectedProduct.setTitle("iPhone 15 Pro Max");
        expectedProduct.setImage("some image");
        expectedProduct.setCategory("mobile phones");
        expectedProduct.setDescription("Best iPhone Ever");


        when(productService.createProduct(any())).thenReturn(expectedProduct);

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(
                        content().string(objectMapper.writeValueAsString(expectedProduct))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is("61c84078-da0a-4b8e-9f9f-59e0b43a6c05")))
                .andExpect(jsonPath("$.title",is("iPhone 15 Pro Max")))

        ;

    }

    void throwExceptionWhenProductNotExist() throws Exception {
        // Arrange :

        when(productService.getProductByID(UUID.fromString("f893fb45-8e2f-4996-8f50-4ebbfab55f1e"))).thenThrow(NotFoundException.class) ;

        mockMvc.perform(
                get("/product/{id}","f893fb45-8e2f-4996-8f50-4ebbfab55f1e")
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.reason",is("Product with id f893fb45-8e2f-4996-8f50-4ebbfab55f1e doesn't exist")))
        ;
    }
}
