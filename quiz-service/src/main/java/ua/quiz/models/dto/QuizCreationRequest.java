package ua.quiz.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ua.category.models.domain.Category;
import ua.question.models.QuestionDto;

import java.util.List;

/**
 * @author (ozhytary)
 */
public record QuizCreationRequest(@NotBlank String name,
                                  @NotBlank String description,
                                  @NotNull String category,
                                  List<QuestionDto> questions
) {

}
