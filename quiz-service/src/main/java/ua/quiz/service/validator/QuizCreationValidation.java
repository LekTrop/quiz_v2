package ua.quiz.service.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import ua.common.validation.AbstractRequiredFieldsValidator;
import ua.exception.QuizBadRequestException;
import ua.question.service.validation.QuestionCreationValidator;
import ua.quiz.models.domain.Quiz;

import static ua.exception.QuizErrorRegister.ENTITY_CANNOT_BE_NULL_EXCEPTION;
import static ua.exception.QuizErrorRegister.ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION;

/**
 * @author (ozhytary)
 */
@Component
public class QuizCreationValidation extends AbstractRequiredFieldsValidator {

    private final static String FIELD_NAME = "name";
    private final static String FIELD_DESCRIPTION = "description";
    private final static String FIELD_CATEGORY = "category";

    private final QuestionCreationValidator questionCreationValidator;
    private Quiz quiz;

    public QuizCreationValidation(QuestionCreationValidator questionCreationValidator) {
        this.questionCreationValidator = questionCreationValidator;
    }

    public QuizCreationValidation quiz(final Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    @Override
    public void validate() {
        super.validate();

        quiz.getQuestions()
            .forEach(question -> questionCreationValidator.question(question)
                                                          .validate()
            );
    }

    @Override
    protected void validateRequiredFields() {
        if (quiz == null) {
            throw new QuizBadRequestException(ENTITY_CANNOT_BE_NULL_EXCEPTION);
        }

        if (StringUtils.isBlank(quiz.getName())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_NAME);
        }

        if (StringUtils.isBlank(quiz.getDescription())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_DESCRIPTION);
        }

        if (quiz.getCategory() == null) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_CATEGORY);
        }
    }
}
