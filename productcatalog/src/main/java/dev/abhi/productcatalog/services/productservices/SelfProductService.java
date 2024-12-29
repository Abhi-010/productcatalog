package dev.abhi.productcatalog.services.productservices;

import dev.abhi.productcatalog.Repository.CategoryRepository;
import dev.abhi.productcatalog.Repository.ProductRepository;
import dev.abhi.productcatalog.dtos.GenericProductDto;
import dev.abhi.productcatalog.dtos.NotFoundExceptionDto;
import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Category;
import dev.abhi.productcatalog.models.Currency;
import dev.abhi.productcatalog.models.Product;
import dev.abhi.productcatalog.services.categoryservices.CategoryService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@Qualifier("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository ;
    private final CategoryRepository categoryRepository ;
    private final CategoryService categoryService ;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository,CategoryService categoryService){
        this.productRepository = productRepository ;
        this.categoryRepository = categoryRepository ;
        this.categoryService = categoryService ;
    }

    @Override
    public GenericProductDto getProductByID(UUID id) throws NotFoundException {

        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new NotFoundException("Product is not available");
        }
        return convertToGenericProductDto(product.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product savingProduct = new Product() ;
        savingProduct.setTitle(genericProductDto.getTitle());
        savingProduct.setDescription(genericProductDto.getDescription());
        savingProduct.setImage(genericProductDto.getImage());
        savingProduct.setInventoryCount(genericProductDto.getInventoryCount());

        savingProduct.setCurrency(Currency.INR);
        savingProduct.setPrice(genericProductDto.getPrice());

        try {
            Category existingCategory = categoryRepository.findCategoryByName(genericProductDto.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            savingProduct.setCategory(existingCategory);
        }
        catch (Exception e){
            Category newCategory = new Category() ;
            newCategory.setName(genericProductDto.getCategory());
            savingProduct.setCategory(newCategory);
        }

        Product savedProduct = productRepository.save(savingProduct);
        return convertToGenericProductDto(savedProduct);
    }

    public GenericProductDto convertToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto() ;
       genericProductDto.setId(product.getId().toString());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setCurrency(product.getCurrency().toString());
        genericProductDto.setInventoryCount(product.getInventoryCount());
        genericProductDto.setTitle(product.getTitle());
        return genericProductDto ;
    }


    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> listOfProducts = productRepository.findAll();
        return listOfProducts.stream().map(this::convertToGenericProductDto).collect(Collectors.toList());
    }

    @Override
    public GenericProductDto deleteProductById(String uuid) throws NotFoundException {
       UUID id = UUID.fromString(uuid);
       Optional<Product> optionalProduct = productRepository.findById(id) ;
       if(optionalProduct.isEmpty()){
           throw new NotFoundException("Product with id " + uuid + " not present");
       }
       productRepository.deleteById(id);
       return convertToGenericProductDto(optionalProduct.get()) ;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto,String uuid) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(uuid) ) ;
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Product with id " + uuid + " not available");
        }

        Product oldProduct = optionalProduct.get() ;
        Optional<Category> optionalCategory  = categoryRepository.findCategoryByName(genericProductDto.getCategory());
        if(optionalCategory.isEmpty()){
            Category category = new Category() ;
            category.setName(genericProductDto.getCategory());
            Category savedCategory = categoryRepository.save(category) ;
            oldProduct.setCategory(savedCategory);
        }
        oldProduct.setImage(genericProductDto.getImage());
        oldProduct.setTitle(genericProductDto.getTitle());
        oldProduct.setPrice(genericProductDto.getPrice());
        oldProduct.setInventoryCount(genericProductDto.getInventoryCount());

        productRepository.save(oldProduct) ;
        return convertToGenericProductDto(oldProduct) ;
    }

    @Override
    public int getProductCountWith(String categoryName, long id) {
        return 0;
    }

    @Override
    public List<GenericProductDto> getProductByCategory(String categoryName) throws NotFoundException {
        List<Product> listOfProducts = categoryService.getAllProductsByCategoryName(categoryName);
        return listOfProducts.stream().map(this::convertToGenericProductDto).toList() ;
    }
}
