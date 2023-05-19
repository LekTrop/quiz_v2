package ua.quiz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.quiz.models.dto.QuizSearchRequest;

/**
 * @author (ozhytary)
 */
@Component
public class QuizSearchRequestCorrector {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private final Integer defaultPageSize;

    public QuizSearchRequestCorrector(final @Value("${quiz.search.page.size}") Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public void correctRequest(final QuizSearchRequest quizSearchRequest) {
        if(quizSearchRequest.getPageSize() == null || quizSearchRequest.getPageSize() < 1){
            quizSearchRequest.setPageSize(defaultPageSize);
        }

        if(quizSearchRequest.getPageNumber() == null || quizSearchRequest.getPageNumber() < 0){
            quizSearchRequest.setPageNumber(DEFAULT_PAGE_NUMBER);
        }
    }
}
