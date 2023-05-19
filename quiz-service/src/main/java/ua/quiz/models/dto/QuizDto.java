package ua.quiz.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.category.models.dto.CategoryDto;
import ua.question.models.QuestionDto;
import ua.question.models.QuizStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author (ozhytary)
 */
public record QuizDto(
        String quizId,
        String name,
        String description,
        LocalDateTime createdAt,
        QuizStatus quizStatus,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) List<QuestionDto> questions,
        CategoryDto category
) {

}
