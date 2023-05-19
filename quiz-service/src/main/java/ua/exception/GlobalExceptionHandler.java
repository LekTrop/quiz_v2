package ua.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author (ozhytary)
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(QuizBadRequestException.class)
    private ResponseEntity<ErrorResponse> handleBadRequestException(final QuizBadRequestException ex) {
        log.error("Exception during processing", ex);
        final ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getErrorCode(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleEntityNotFoundException(final QuizNotFoundException ex) {
        log.error("Exception during processing", ex);
        final ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getErrorCode(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
