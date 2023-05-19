package ua.common.validation;

/**
 * @author (ozhytary)
 */
public abstract class AbstractRequiredFieldsValidator extends Validator {
    @Override
    public void validate() {
        validateRequiredFields();
    }

    abstract protected void validateRequiredFields();
}
