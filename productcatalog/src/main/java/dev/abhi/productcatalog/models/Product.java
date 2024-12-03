package dev.abhi.productcatalog.models;

import lombok.Setter;

@Setter
public class Product extends BaseModel{
    private String title ;
    private String description ;
    private String image ;
    private Category category ;
    private double price ;
}
