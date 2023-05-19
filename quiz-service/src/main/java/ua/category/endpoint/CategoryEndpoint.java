package ua.category.endpoint;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.category.models.CategoryCreationRequest;
import ua.category.models.dto.CategoryDto;
import ua.category.models.mapper.CategoryMapper;
import ua.category.service.CategoryService;
import ua.exception.QuizNotFoundException;

import static ua.exception.QuizErrorRegister.ENTITY_BY_NAME_NOT_FOUND_EXCEPTION;

/**
 * @author (ozhytary)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryEndpoint {
    @NonNull
    private final CategoryService categoryService;
    @NonNull
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<?> save(final @RequestBody CategoryCreationRequest categoryCreationRequest) {
        categoryService.save(categoryCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .build();
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDto> findByName(final @PathVariable("name") String name) {
        final var category =
                categoryService.findByName(name)
                               .map(categoryMapper::toDto)
                               .orElseThrow(() -> new QuizNotFoundException(ENTITY_BY_NAME_NOT_FOUND_EXCEPTION, name));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
