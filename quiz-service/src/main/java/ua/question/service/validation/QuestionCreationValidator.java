package ua.question.service.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import ua.common.validation.AbstractRequiredFieldsValidator;
import ua.exception.QuizBadRequestException;
import ua.question.models.Question;

import static ua.exception.QuizErrorRegister.*;

/**
 * @author (ozhytary)
 */
@Component
public class QuestionCreationValidator extends AbstractRequiredFieldsValidator {

    private final static String FIELD_TEXT = "text";
    private final static String FIELD_QUIZ = "quiz";
    private final static String FIELD_ANSWER_DESCRIPTION = "answerDescription";
    private final static String FIELD_CORRECT_ANSWER_DESCRIPTION = "correctAnswerDescription";
    private final static String FIELD_CORRECT_ANSWERS = "correctAnswers";

    private Question question;

    public QuestionCreationValidator question(final Question question){
        this.question = question;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }

    @Override
    protected void validateRequiredFields() {
        if(question == null){
            throw new QuizBadRequestException(ENTITY_CANNOT_BE_NULL_EXCEPTION);
        }

        if(StringUtils.isBlank(question.getText())){
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_TEXT);
        }

        if(StringUtils.isBlank(question.getAnswerDescription())){
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_CORRECT_ANSWER_DESCRIPTION );
        }

        if(question.getCorrectAnswers() == null){
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_CORRECT_ANSWERS);
        }

        if(question.getCorrectAnswers() <= 0){
            throw new QuizBadRequestException(CORRECT_ANSWER_LESS_OR_EQUALS_THEN_ZERO_EXCEPTION, question.getCorrectAnswers());
        }

        if(question.getQuiz() == null){
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_QUIZ);
        }
    }
}
