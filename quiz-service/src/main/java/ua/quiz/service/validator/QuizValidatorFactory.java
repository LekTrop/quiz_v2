package ua.quiz.service.validator;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author (ozhytary)
 */
@Component
@RequiredArgsConstructor
@Getter
public class QuizValidatorFactory {
    @NonNull
    private final QuizCreationValidation quizCreationValidation;
}
