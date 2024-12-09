package dev.abhi.productcatalog.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="locations")
public class Location extends BaseModel{

    private String name;
    private int area_code ;
    private String landmark ; 
    private String comment ;

}
