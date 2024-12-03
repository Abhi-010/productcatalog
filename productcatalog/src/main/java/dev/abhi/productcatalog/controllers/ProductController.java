package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.dtos.NotFoundExceptionDto;
import dev.abhi.productcatalog.exceptions.ApiException;
import dev.abhi.productcatalog.exceptions.ArgumentMisMatchException;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.requestBody.ProductRequestBody;
import dev.abhi.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/products")
 public class ProductController {

    private final ProductService productService ;

    public ProductController(ProductService productService){
        this.productService = productService ;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") long id) throws NotFoundException {
         return productService.getProductByID(id);
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
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") long id){
        //System.out.println("id ----" + id);
        GenericProductDto genericProductDto =  productService.deleteProductById(id) ;

        return new ResponseEntity<>(genericProductDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GenericProductDto> updateProductByID(@RequestBody GenericProductDto genericProductDto ,@PathVariable("id") long id){

        GenericProductDto genericProductDto1 = productService.updateProductById(genericProductDto,id) ;
        return new ResponseEntity<>(genericProductDto1,HttpStatus.OK) ;
    }
}
