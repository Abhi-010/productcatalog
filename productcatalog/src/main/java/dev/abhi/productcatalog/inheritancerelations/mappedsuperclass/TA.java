package dev.abhi.productcatalog.inheritancerelations.mappedsuperclass;

import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="ms_ta")
public class TA extends User{
    private double averageRating ;
}
