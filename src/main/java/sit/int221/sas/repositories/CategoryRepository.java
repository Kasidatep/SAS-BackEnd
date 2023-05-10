package sit.int221.sas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sit.int221.sas.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsCategoryByCategoryName(String categoryName);
}
