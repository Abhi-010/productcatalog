package dev.abhi.productcatalog.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCategoryDto {
     String name;

     public FakeStoreCategoryDto(String name) {
          this.name = name;
     }
}
