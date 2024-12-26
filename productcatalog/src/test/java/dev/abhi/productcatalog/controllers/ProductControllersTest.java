package dev.abhi.productcatalog.controllers;


import dev.abhi.productcatalog.exceptions.NotFoundException;
import dev.abhi.productcatalog.models.Product;
import dev.abhi.productcatalog.thirdpartyclients.FakeStore.FakeStoreProductServiceClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ProductControllersTest {

    @Autowired
    FakeStoreProductServiceClient fakeStoreProductServiceClient ;
//    public ProductControllersTest(FakeStoreProductServiceClient fakeStoreProductServiceClient){
//        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient ;
//    }

    @Test
    void testProductWithId() throws NotFoundException {
        assertNull(fakeStoreProductServiceClient.getProductByID(101L));
    }


    @Test
    @DisplayName("some name..")
    void testOnePlusTwoEqualsTrue(){
        //System.out.println("this is a test method");
        assert (1+1==2) ;
    }
}
