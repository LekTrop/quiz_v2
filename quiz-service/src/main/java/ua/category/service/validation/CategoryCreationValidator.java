package ua.category.service.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.category.models.domain.Category;
import ua.category.service.CategoryService;
import ua.common.validation.AbstractRequiredFieldsValidator;
import ua.exception.QuizBadRequestException;

import static ua.exception.QuizErrorRegister.*;

/**
 * @author (ozhytary)
 */
@Component
public class CategoryCreationValidator extends AbstractRequiredFieldsValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";

    private Category category;
    @Lazy
    @Autowired
    private CategoryService categoryService;

    public CategoryCreationValidator category(final Category category) {
        this.category = category;
        return this;
    }

    @Override
    public void validate() {
        super.validate();

        if (categoryService.findByName(category.getName())
                           .isPresent()) {
            throw new QuizBadRequestException(ENTITY_ALREADY_EXIST_EXCEPTION, category.getName());
        }
    }

    @Override
    protected void validateRequiredFields() {
        if (category == null) {
            throw new QuizBadRequestException(ENTITY_CANNOT_BE_NULL_EXCEPTION);
        }

        if (StringUtils.isBlank(category.getName())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_NAME);
        }

        if (StringUtils.isBlank(category.getDescription())) {
            throw new QuizBadRequestException(ENTITY_MISSED_REQUIRED_FIELD_EXCEPTION, FIELD_DESCRIPTION);
        }
    }
}
