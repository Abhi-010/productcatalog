package dev.abhi.productcatalog.Repository;

import dev.abhi.productcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
