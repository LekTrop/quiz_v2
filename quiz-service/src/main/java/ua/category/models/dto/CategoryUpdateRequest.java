package ua.category.models.dto;

/**
 * @author (ozhytary)
 */
public record CategoryUpdateRequest(String name,
                                    String description,
                                    String parentCategory
) {
}
