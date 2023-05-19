package ua.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.category.models.domain.Category;

import java.util.Optional;

/**
 * @author (ozhytary)
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
