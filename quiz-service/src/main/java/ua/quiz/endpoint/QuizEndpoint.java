package ua.quiz.endpoint;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.exception.QuizNotFoundException;
import ua.quiz.models.dto.QuizSearchRequest;
import ua.quiz.models.domain.Quiz;
import ua.quiz.models.dto.QuizCreationRequest;
import ua.quiz.models.dto.QuizDto;
import ua.quiz.models.mapper.QuizMapper;
import ua.quiz.service.QuizService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static ua.exception.QuizErrorRegister.ENTITY_NOT_FOUND_EXCEPTION;

/**
 * @author (ozhytary)
 */
@RestController
@RequestMapping("api/v1/quizzes")
@RequiredArgsConstructor
@Validated
public class QuizEndpoint {
    @NonNull
    private final QuizService quizService;
    @NonNull
    private final QuizMapper quizMapper;

    @PostMapping
    public ResponseEntity<?> save(final @Valid @RequestBody QuizCreationRequest quizCreationRequest) {
        quizService.save(quizCreationRequest);
        return ResponseEntity.status(CREATED)
                             .build();
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> findAll(final QuizSearchRequest quizSearchRequest) {
        final List<QuizDto> quizzes = quizService.findAll(quizSearchRequest)
                                                 .stream()
                                                 .map(quizMapper::toDto)
                                                 .collect(Collectors.toList());

        return new ResponseEntity<>(quizzes, OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<QuizDto> findById(final @PathVariable("id") String quizId) {
        final Quiz quiz = quizService.findById(quizId)
                                     .orElseThrow(() -> new QuizNotFoundException(ENTITY_NOT_FOUND_EXCEPTION, quizId));

        return new ResponseEntity<>(quizMapper.toDto(quiz), OK);
    }
}
