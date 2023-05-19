package ua.exception;

/**
 * @author (ozhytary)
 */
public enum QuizErrorRegister implements ErrorRegister {
    ENTITY_NOT_FOUND_EXCEPTION("Entity with id: [%s] does not exist", 1),
    ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION("Entity missed required field: [%s]", 2),
    ENTITY_CANNOT_BE_NULL_EXCEPTION("Entity can`t be null", 3),
    CORRECT_ANSWER_LESS_OR_EQUALS_THEN_ZERO_EXCEPTION("Correct answer number should be more then 0, but correct answer: [%s]", 4),
    ENTITY_BY_NAME_NOT_FOUND_EXCEPTION("Entity with name: [%s] does not exist", 5),
    ENTITY_ALREADY_EXIST_EXCEPTION("Entity with name: [%s] already exist", 6);

    private final String message;
    private final int errorCode;

    QuizErrorRegister(final String message, final int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getFormattedMessage(Object... args) {
        return String.format(message, args);
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
