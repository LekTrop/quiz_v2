package ua.quiz.models.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ua.category.models.domain.Category;
import ua.common.audit.AbstractDbAuditing;
import ua.question.models.Question;
import ua.question.models.QuizStatus;

import java.util.ArrayList;
import java.util.List;

import static ua.question.models.QuizStatus.DRAFT;

/**
 * @author (ozhytary)
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "quizzes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Quiz extends AbstractDbAuditing{
    @Id
    @UuidGenerator
    @Column(name = "quiz_id")
    private String quizId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuizStatus quizStatus;

    @ManyToOne
    @JoinColumn(name = "fk_category_id", nullable = false)
    private Category category;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "quiz", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        if(quizStatus == null){
            quizStatus = DRAFT;
        }
    }
}
