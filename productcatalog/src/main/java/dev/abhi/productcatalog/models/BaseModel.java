package dev.abhi.productcatalog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
//    @Id
//    @GeneratedValue(generator = "uuidgenerator")
//    @GenericGenerator(name="uuidgenerator",strategy = "uuid2")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
}
