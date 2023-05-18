package ua.question.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import ua.answer.models.Answer;
import ua.common.audit.AbstractDbAuditing;

import java.util.ArrayList;
import java.util.List;

/**
 * @author (ozhytary)
 */
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "questions")
@Builder(toBuilder = true)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuestionStatus questionStatus;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        if(questionStatus == null){
            questionStatus = QuestionStatus.DRAFT;
        }
    }
}
