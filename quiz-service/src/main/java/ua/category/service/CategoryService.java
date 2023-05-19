package ua.category.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import ua.category.models.CategoryCreationRequest;
import ua.category.models.domain.Category;
import ua.category.models.mapper.CategoryMapper;
import ua.category.repository.CategoryRepository;
import ua.category.service.validation.CategoryValidatorFactory;
import ua.exception.QuizNotFoundException;

import java.util.Optional;

import static ua.exception.QuizErrorRegister.ENTITY_BY_NAME_NOT_FOUND_EXCEPTION;

/**
 * @author (ozhytary)
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    @NonNull
    private final CategoryRepository categoryRepository;
    @NonNull
    private final CategoryMapper categoryMapper;
    @NonNull
    private final CategoryValidatorFactory categoryValidatorFactory;

    public Category save(final CategoryCreationRequest categoryCreationRequest) {
        final var category = categoryMapper.toDomain(categoryCreationRequest);
        categoryValidatorFactory.getCategoryCreationValidator()
                                .category(category)
                                .validate();
        category.setParentCategory(getParentCategory(categoryCreationRequest));
        return categoryRepository.save(category);
    }

    public Optional<Category> findByName(final String name) {
        return categoryRepository.findByName(name);
    }

    private Category getParentCategory(CategoryCreationRequest categoryCreationRequest) {
        return StringUtils.isBlank(categoryCreationRequest.parentName())
                ? null
                : findByName(categoryCreationRequest.parentName()).orElseThrow(() -> new QuizNotFoundException(ENTITY_BY_NAME_NOT_FOUND_EXCEPTION, categoryCreationRequest.name())
        );
    }
}
