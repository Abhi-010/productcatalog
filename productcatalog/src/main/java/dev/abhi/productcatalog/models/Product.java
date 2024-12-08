package dev.abhi.productcatalog.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Product extends BaseModel{
    private String title ;
    private String description ;
    private String image ;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType. DETACH})
    private Category category ;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Price price ;

    private int inventoryCount ;
}
