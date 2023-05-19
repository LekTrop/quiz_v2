package ua.category.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author (ozhytary)
 */
public record CategoryDto(String name,
                          String description,
                          @JsonInclude(JsonInclude.Include.NON_NULL) CategoryDto parentCategory,
                          @JsonInclude(JsonInclude.Include.NON_EMPTY)List<CategoryDto> childCategories) {
}
