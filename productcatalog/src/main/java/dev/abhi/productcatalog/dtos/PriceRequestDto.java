package dev.abhi.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequestDto {
    private String currency ;
    private int price ;
}
