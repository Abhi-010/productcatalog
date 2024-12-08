package dev.abhi.productcatalog.models;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
//    @Id
//    @GeneratedValue(generator = "uuidgenerator")
//    @GenericGenerator(name="uuidgenerator",strategy = "uuid2")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
}
