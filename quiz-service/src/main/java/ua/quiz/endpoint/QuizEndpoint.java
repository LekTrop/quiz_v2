package ua.quiz.endpoint;

import com.github.fge.jsonpatch.JsonPatch;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.exception.QuizNotFoundException;
import ua.quiz.models.domain.Quiz;
import ua.quiz.models.dto.QuizCreationRequest;
import ua.quiz.models.dto.QuizDto;
import ua.quiz.models.dto.QuizSearchRequest;
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
@Slf4j
public class QuizEndpoint {
    @NonNull
    private final QuizService quizService;
    @NonNull
    private final QuizMapper quizMapper;

    @PostMapping
    public ResponseEntity<QuizDto> save(final @Valid @RequestBody QuizCreationRequest quizCreationRequest) {
        log.info("save.E QuizCreationRequest: {}", quizCreationRequest);
        final QuizDto savedQuiz = quizMapper.toDto(quizService.save(quizCreationRequest));
        final var responseEntity = new ResponseEntity<>(savedQuiz, CREATED);
        log.info("save.X response: {}", responseEntity);
        return responseEntity;
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

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<QuizDto> update(final @PathVariable("id") String id, final @RequestBody JsonPatch jsonPatch) {
        quizService.update(id, jsonPatch);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                             .build();
    }
}
