package ua.category.service.validation;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author (ozhytary)
 */
@RequiredArgsConstructor
@Getter
@Component
public class CategoryValidatorFactory {
    @NonNull
    private final CategoryCreationValidator categoryCreationValidator;
}
