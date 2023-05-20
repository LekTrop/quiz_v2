package ua.answer.service.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import ua.answer.models.Answer;
import ua.common.validation.AbstractRequiredFieldsValidator;
import ua.exception.QuizBadRequestException;

import static ua.exception.QuizErrorRegister.ENTITY_CANNOT_BE_NULL_EXCEPTION;
import static ua.exception.QuizErrorRegister.ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION;

/**
 * @author (ozhytary)
 */
@Component
public class AnswerCreationValidator extends AbstractRequiredFieldsValidator {

    private final static String FIELD_TEXT = "text";
    private final static String FIELD_CORRECT = "correct";

    private Answer answer;

    public AnswerCreationValidator answer(final Answer answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public void validate() {
        super.validate();

    }

    @Override
    protected void validateRequiredFields() {
        if (answer == null) {
            throw new QuizBadRequestException(ENTITY_CANNOT_BE_NULL_EXCEPTION);
        }
        if (StringUtils.isBlank(answer.getText())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_TEXT);
        }
        if (answer.getCorrect() == null) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_TEXT);
        }
    }
}
