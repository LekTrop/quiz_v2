package ua.quiz.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.category.models.domain.Category;
import ua.category.service.CategoryService;
import ua.exception.QuizErrorRegister;
import ua.exception.QuizNotFoundException;
import ua.quiz.models.domain.Quiz;
import ua.quiz.models.dto.QuizCreationRequest;
import ua.quiz.models.dto.QuizSearchRequest;
import ua.quiz.models.mapper.QuizMapper;
import ua.quiz.repo.QuizRepository;
import ua.quiz.service.validator.QuizValidatorFactory;

import java.util.List;
import java.util.Optional;

import static ua.exception.QuizErrorRegister.ENTITY_BY_NAME_NOT_FOUND_EXCEPTION;
import static ua.exception.QuizErrorRegister.ENTITY_NOT_FOUND_EXCEPTION;

/**
 * @author (ozhytary)
 */
@Service
@Valid
@RequiredArgsConstructor
public class QuizService {
    @NonNull
    private final QuizRepository quizRepository;
    @NonNull
    private final CategoryService categoryService;
    @NonNull
    private final QuizMapper quizMapper;
    @NonNull
    private final QuizValidatorFactory quizValidatorFactory;
    @NonNull
    private final QuizSearchRequestCorrector quizSearchRequestCorrector;

    public Quiz save(final @Validated QuizCreationRequest quizCreationRequest) {
        final Quiz quiz = quizMapper.toDomain(quizCreationRequest);
        final Category category = categoryService.findByName(quizCreationRequest.category())
                                                 .orElseThrow(() -> new QuizNotFoundException(ENTITY_BY_NAME_NOT_FOUND_EXCEPTION, quizCreationRequest.name()));
        quiz.setCategory(category);
        quizValidatorFactory.getQuizCreationValidation()
                            .quiz(quiz)
                            .validate();

        return quizRepository.save(quiz);
    }

    public Optional<Quiz> findById(final String quizId) {
        return quizRepository.findById(quizId);
    }

    public List<Quiz> findAll(final QuizSearchRequest quizSearchRequest) {
        quizSearchRequestCorrector.correctRequest(quizSearchRequest);

        final Pageable pageable = PageRequest.of(quizSearchRequest.getPageNumber(), quizSearchRequest.getPageSize());

        return quizRepository.findAll(quizSearchRequest.getQuery(), pageable)
                             .getContent();
    }
}
