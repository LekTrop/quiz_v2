package ua.quiz.models.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author (ozhytary)
 */
@Data
@Builder(toBuilder = true)
public class QuizSearchRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String query;
}
