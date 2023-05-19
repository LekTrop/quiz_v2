package ua.exception;

/**
 * @author (ozhytary)
 */
public interface ErrorRegister {
    String getFormattedMessage(final Object... args);

    int getErrorCode();
}
