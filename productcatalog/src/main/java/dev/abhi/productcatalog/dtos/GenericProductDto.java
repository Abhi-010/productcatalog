package dev.abhi.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class GenericProductDto {
    private String Id ;
    private String title ;
    private double price ;
    private String currency ;
    private String category ;
    private String description ;
    private String image ;
    private int inventoryCount ;
}
