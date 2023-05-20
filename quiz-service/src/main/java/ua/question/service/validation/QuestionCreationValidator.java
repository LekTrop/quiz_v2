package ua.question.service.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.answer.models.Answer;
import ua.answer.service.validation.AnswerCreationValidator;
import ua.common.validation.AbstractRequiredFieldsValidator;
import ua.exception.QuizBadRequestException;
import ua.question.models.Question;

import java.util.Objects;

import static ua.exception.QuizErrorRegister.*;

/**
 * @author (ozhytary)
 */
@Component
public class QuestionCreationValidator extends AbstractRequiredFieldsValidator {

    private final static String FIELD_TEXT = "text";
    private final static String FIELD_QUIZ = "quiz";
    private final static String FIELD_CORRECT_ANSWER_DESCRIPTION = "correctAnswerDescription";
    private final static String FIELD_CORRECT_ANSWERS = "correctAnswers";

    private Question question;
    private final AnswerCreationValidator answerCreationValidator;

    public QuestionCreationValidator(@Lazy AnswerCreationValidator answerCreationValidator) {
        this.answerCreationValidator = answerCreationValidator;
    }

    public QuestionCreationValidator question(final Question question) {
        this.question = question;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
        validateAnswers();
        validateAnswerCorrectNumber();
    }

    private void validateAnswers() {
        question.getAnswers()
                .forEach(answer -> {
                    answerCreationValidator.answer(answer)
                                           .validate();
                });
    }

    @Override
    protected void validateRequiredFields() {
        if (question == null) {
            throw new QuizBadRequestException(ENTITY_CANNOT_BE_NULL_EXCEPTION);
        }

        if (StringUtils.isBlank(question.getText())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_TEXT);
        }

        if (StringUtils.isBlank(question.getAnswerDescription())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_CORRECT_ANSWER_DESCRIPTION);
        }

        if (question.getCorrectAnswers() == null) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_CORRECT_ANSWERS);
        }

        if (question.getCorrectAnswers() <= 0) {
            throw new QuizBadRequestException(CORRECT_ANSWER_LESS_OR_EQUALS_THEN_ZERO_EXCEPTION, question.getCorrectAnswers());
        }

        if (question.getQuiz() == null) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_QUIZ);
        }
    }

    private void validateAnswerCorrectNumber() {
        if (!question.getAnswers()
                     .isEmpty()) {
            int correctAnswersNumber = (int) question.getAnswers()
                                                     .stream()
                                                     .filter(Answer::getCorrect)
                                                     .count();

            if (!Objects.equals(correctAnswersNumber, question.getCorrectAnswers())) {
                throw new QuizBadRequestException(
                        QUESTION_CORRECT_ANSWERS_NOT_EQUALS_WITH_CORRECT_ANSWERS,
                        question.getCorrectAnswers(),
                        correctAnswersNumber
                );
            }
        }
    }
}
