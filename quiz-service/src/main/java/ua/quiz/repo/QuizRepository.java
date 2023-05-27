package ua.quiz.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.quiz.models.domain.Quiz;

/**
 * @author (ozhytary)
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {

    @Query("""
            SELECT q FROM Quiz q
            WHERE (:query IS NULL OR (q.description LIKE :query OR q.name LIKE :query))
            """)
    Page<Quiz> findAll(final @Param("query") String query,
                       final @Param("pageable") Pageable pageable);
}
