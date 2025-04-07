package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.security.JwtObject;
import dev.abhi.productcatalog.security.TokenValidator;
import dev.abhi.productcatalog.services.productservices.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
 public class ProductController {

    private final ProductService productService ;
    private final TokenValidator tokenValidator ;

    public ProductController(ProductService productService, TokenValidator tokenValidator){
        this.productService = productService ;
        this.tokenValidator = tokenValidator ;
    }

    /*
    New way of Handling Exception is by ResponseExceptionStatus Class
     */
    @GetMapping("{id}")
    public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken
            ,@PathVariable("id") String id) {
        try{
            System.out.println("Auth token :: " + authToken);

            Optional<JwtObject> jwtObjectOptional =
                    tokenValidator.validateToken(1L,authToken);

            return productService.getProductByID(UUID.fromString(id));
        }
        catch (NotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product with id " + id + " doesn't exist", notFoundException);
        }
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts() ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") String uuid) throws NotFoundException {
        GenericProductDto genericProductDto =  productService.deleteProductById(uuid) ;
        return new ResponseEntity<>(genericProductDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GenericProductDto> updateProductByID(@RequestBody GenericProductDto genericProductDto ,@PathVariable("id") String id) throws NotFoundException {
        GenericProductDto genericProductDto1 = productService.updateProductById(genericProductDto,id) ;
        return new ResponseEntity<>(genericProductDto1,HttpStatus.OK) ;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @GetMapping("/count")
    public String getProductCountWith(@RequestParam(value = "categoryName") String categoryName,
                                   @RequestParam(value="id",required = false,defaultValue = "5") long id){
        return "The number of Products with Category Name : " + categoryName +
                " and id : " + id + " is " + productService.getProductCountWith(categoryName,id);
    }

    @GetMapping("/category/{categoryName}")
    public List<GenericProductDto> getProductByCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        return productService.getProductByCategory(categoryName);
    }


}
