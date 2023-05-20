package ua.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author (ozhytary)
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String DELIMITER_BETWEEN_ERROR_FIELDS = ";";

    @ExceptionHandler(QuizBadRequestException.class)
    private ResponseEntity<ErrorResponse> handleBadRequestException(final QuizBadRequestException ex) {
        log.error("Exception during processing", ex);
        final ErrorResponse errorResponse =
                new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleEntityNotFoundException(final QuizNotFoundException ex) {
        log.error("Exception during processing", ex);
        final ErrorResponse errorResponse =
                new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorResponse> handleEntityNotFoundException(final MethodArgumentNotValidException ex) {
        log.error("Exception during processing", ex);
        final ErrorResponse errorResponse =
                new ErrorResponse(getMessage(ex), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private String getMessage(final MethodArgumentNotValidException ex) {
        final StringBuilder builder = new StringBuilder();
        final List<FieldError> fieldErrors = ex.getFieldErrors();

        for (final var field : fieldErrors) {
            builder.append(field.getField())
                   .append(" ")
                   .append(field.getDefaultMessage())
                   .append(DELIMITER_BETWEEN_ERROR_FIELDS);

        }

        return builder.toString();
    }
}
