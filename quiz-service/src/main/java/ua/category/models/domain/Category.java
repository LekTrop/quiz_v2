package ua.category.models.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;
import ua.common.audit.AbstractDbAuditing;
import ua.quiz.models.domain.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author (ozhytary)
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Category extends AbstractDbAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.PERSIST)
    private List<Category> childCategories;

    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Quiz> quizzes = new ArrayList<>();
}
