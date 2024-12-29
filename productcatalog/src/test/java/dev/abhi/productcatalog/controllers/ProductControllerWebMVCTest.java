package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.services.productservices.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {

    @Autowired
    private MockMvc mockMvc ;

    @MockBean
    private ProductService productService ;

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {

        // arrange :
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(
                get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]")
        ) ;
    }
}
