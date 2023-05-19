package ua.question.models;

import ua.answer.models.AnswerDto;

import java.util.List;

/**
 * @author (ozhytary)
 */
public record QuestionDto(
        String questionId,
        String text,
        Integer correctAnswers,
        String answerDescription,
        List<AnswerDto> answers) {
}
