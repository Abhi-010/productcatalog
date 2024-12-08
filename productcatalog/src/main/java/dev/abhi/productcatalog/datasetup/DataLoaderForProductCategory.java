package dev.abhi.productcatalog.datasetup;

import dev.abhi.productcatalog.Repository.CategoryRepository;
import dev.abhi.productcatalog.Repository.ProductRepository;
import dev.abhi.productcatalog.models.Category;
import dev.abhi.productcatalog.models.Currency;
import dev.abhi.productcatalog.models.Price;
import dev.abhi.productcatalog.models.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoaderForProductCategory {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public DataLoaderForProductCategory(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void loadData() {

//        List<String> categoriesList = Arrays.asList("electronics","jewelery","men's clothing",
//                "women's clothing");
//
//        for(var obj : categoriesList){
//            Category category = new Category() ;
//            category.setName(obj);
//            categoryRepository.save(category);
//        }

//
//        insertProducts();
//
//        List<Category> categoryList1 = categoryRepository.findAllByName("jewelery");
//        categoryList1.forEach(System.out::println);
    }
    public void insertProducts(){
        Product product = new Product();
        product.setTitle("Samsung 49-Inch CHG90 144Hz Curved Gaming Monitor (LC49HG90DMNXZA) – Super Ultrawide Screen QLED");
        product.setDescription("49 INCH SUPER ULTRAWIDE 32:9 CURVED GAMING MONITOR with dual 27 inch screen side by side QUANTUM DOT (QLED) TECHNOLOGY, HDR support and  ghosting, and reduce input lag");
        product.setImage("https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg");

        Price price = new Price();
        price.setCurrency(Currency.EURO);
        price.setPrice(999);
        product.setPrice(price);

        Category category = new Category();
        category.setName("jewelery");
        product.setCategory(category);

        productRepository.save(product);



        product = new Product();

        product.setTitle("Acer SB220Q bi 21.5 inches Full HD (1920 x 1080)");
        product.setDescription("21. 5 inches Full HD (1920 x 1080) widescreen IPS display");
        product.setImage("https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg");

        price = new Price();
        price.setCurrency(Currency.USD);
        price.setPrice(100);
        product.setPrice(price);

        category = new Category() ;
        category.setName("electronics");
        product.setCategory(category);

        productRepository.save(product);

        product = new Product();
        product.setTitle("Mens Casual Slim Fit");
        product.setDescription("The color could be slightly different between on the screen and in practice");
        product.setImage("https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg");
        price = new Price();
        price.setCurrency(Currency.INR);
        price.setPrice(134);
        product.setPrice(price);

        category = new Category() ;
        category.setName("men's clothing");
        product.setCategory(category);

        productRepository.save(product);


        product = new Product();
        product.setTitle("Solid Gold Petite Micropave");
        product.setDescription("Satisfaction Guaranteed. Return or exchange any order");
        product.setImage("https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg");
        price = new Price();
        price.setCurrency(Currency.INR);
        price.setPrice(140);
        product.setPrice(price);

        category = new Category() ;
        category.setName("jewelery");
        product.setCategory(category);

        productRepository.save(product);
    }
}
