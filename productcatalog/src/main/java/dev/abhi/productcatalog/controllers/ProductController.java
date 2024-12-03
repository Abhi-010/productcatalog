package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.requestBody.ProductRequestBody;
import dev.abhi.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
 public class ProductController {

    private final ProductService productService ;

    public ProductController(ProductService productService){
        this.productService = productService ;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") long id){
        return productService.getProductByID(id) ;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts() ;
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") long id){
        //System.out.println("id ----" + id);
        return productService.deleteProductById(id) ;
    }
}
