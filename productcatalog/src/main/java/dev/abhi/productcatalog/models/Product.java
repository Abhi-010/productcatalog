package dev.abhi.productcatalog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="products")
public class Product extends BaseModel{
    private String title ;
    private String description ;
    private String image ;

    private Currency currency ;

    private double price ;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    //@ManyToOne
    @JoinColumn(name = "cat_id",nullable = false)
    private Category category ;

    @Column(name="inven_count",nullable = false,length = 121)
    private int inventoryCount ;
}
