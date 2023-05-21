package ua.question.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ua.answer.models.Answer;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends AbstractDbAuditing {
    @Id
    @Column(name = "question_id")
    @UuidGenerator
    private String questionId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "correct_answers", nullable = false)
    private Integer correctAnswers;

    @Column(name = "answer_description", nullable = false)
    private String answerDescription;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_quiz_id", nullable = false)
    private Quiz quiz;
}
