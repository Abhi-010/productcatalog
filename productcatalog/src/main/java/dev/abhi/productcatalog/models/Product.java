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

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType. DETACH})
    @JoinColumn(name = "cat_id",nullable = false)
    private Category category ;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Price price ;

    @Column(name="inven_count",nullable = false,length = 121)
    private int inventoryCount ;
}
