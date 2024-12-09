package dev.abhi.productcatalog.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="categories")
@Getter
@Setter
public class Category extends BaseModel {

    private String name ;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Product> productList ;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}
