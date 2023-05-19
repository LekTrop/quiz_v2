package ua.exception;

/**
 * @author (ozhytary)
 */
public class QuizNotFoundException extends QuizException{
    public QuizNotFoundException(ErrorRegister errorRegister, Object... args) {
        super(errorRegister, args);
    }

    public QuizNotFoundException(ErrorRegister errorRegister, Throwable cause, Object... args) {
        super(errorRegister, cause, args);
    }
}
