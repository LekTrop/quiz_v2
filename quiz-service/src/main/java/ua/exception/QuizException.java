package ua.exception;

import lombok.Getter;

/**
 * @author (ozhytary)
 */
@Getter
public abstract class QuizException extends RuntimeException {

    private final int errorCode;

    public QuizException(final ErrorRegister errorRegister, final Object... args) {
        super(errorRegister.getFormattedMessage(args));
        this.errorCode = errorRegister.getErrorCode();
    }

    public QuizException(final ErrorRegister errorRegister, Throwable cause, final Object... args) {
        super(errorRegister.getFormattedMessage(args), cause);
        this.errorCode = errorRegister.getErrorCode();
    }
}
