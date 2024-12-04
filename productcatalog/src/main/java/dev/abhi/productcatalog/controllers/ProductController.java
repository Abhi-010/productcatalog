package dev.abhi.productcatalog.controllers;

import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public GenericProductDto getProductById(@PathVariable("id") long id) throws NotFoundException {
         return productService.getProductByID(id);
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

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @GetMapping("/abc")
    public String getProductCountWith(@RequestParam(value = "categoryName") String categoryName,
                                   @RequestParam(value="id",required = false,defaultValue = "5") long id){

        return "The number of Products with Category Name : " + categoryName +
                " and id : " + id + " is " + productService.getProductCountWith(categoryName,id);
    }
}
