package ua.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author (ozhytary)
 */
public record ErrorResponse(String message, Integer errorCode, LocalDateTime timestamp) {
}
