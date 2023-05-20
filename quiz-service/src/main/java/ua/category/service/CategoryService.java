package ua.category.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.category.models.domain.Category;
import ua.category.models.dto.CategoryCreationRequest;
import ua.category.models.dto.CategoryUpdateRequest;
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

    @Transactional
    public Category save(final CategoryCreationRequest categoryCreationRequest) {
        final var category = categoryMapper.toDomain(categoryCreationRequest);
        categoryValidatorFactory.getCategoryCreationValidator()
                                .category(category)
                                .validate();
        category.setParentCategory(getParentCategory(categoryCreationRequest.parentName()));
        return categoryRepository.save(category);
    }

    public Optional<Category> findByName(final String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional
    public Category update(final CategoryUpdateRequest categoryUpdateRequest, final String name) {
        final Category original =
                findByName(name).orElseThrow(() -> new QuizNotFoundException(ENTITY_BY_NAME_NOT_FOUND_EXCEPTION, name));
        final Category parent = getParentCategory(categoryUpdateRequest.parentCategory());

        original.setParentCategory(parent);
        original.setName(categoryUpdateRequest.name());
        original.setDescription(categoryUpdateRequest.description());

        return categoryRepository.save(original);
    }

    private Category getParentCategory(final String name) {
        return StringUtils.isBlank(name)
                ? null
                : findByName(name).orElseThrow(() -> new QuizNotFoundException(ENTITY_BY_NAME_NOT_FOUND_EXCEPTION, name)
        );
    }
}
