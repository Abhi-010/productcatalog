package dev.abhi.productcatalog.Repository;

import dev.abhi.productcatalog.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {

    Optional<Category> findCategoryByName(String categoryName) ;

    List<Category> findAllByNameContains(String matchingName);

   // @Query(value = CustomQueries.FIND_BY_ALL_NAME, nativeQuery = true)
    List<Category> findAllByName(String name);
}
