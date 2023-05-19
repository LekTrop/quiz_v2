package ua.exception;

/**
 * @author (ozhytary)
 */
public class QuizBadRequestException extends QuizException {
    public QuizBadRequestException(ErrorRegister errorRegister, Object... args) {
        super(errorRegister, args);
    }

    public QuizBadRequestException(ErrorRegister errorRegister, Throwable cause, Object... args) {
        super(errorRegister, cause, args);
    }
}
