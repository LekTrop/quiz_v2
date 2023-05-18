package ua.answer.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import ua.common.audit.AbstractDbAuditing;
import ua.question.models.Question;

/**
 * @author (ozhytary)
 */
@Data
@Entity
@Table(name = "answers")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Answer extends AbstractDbAuditing {
    @Id
    @Column(name = "answer_id")
    @UuidGenerator
    private String answerId;
    @Column(name = "text", nullable = false)
    private String text;
    @Column(name = "isCorrect", nullable = false)
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name = "fk_question_id")
    private Question question;
}