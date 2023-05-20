package ua.category.models.dto;

/**
 * @author (ozhytary)
 */
public record CategoryCreationRequest(String name,
                                      String parentName,
                                      String description) {
}
