package ua.category.models;

/**
 * @author (ozhytary)
 */
public record CategoryCreationRequest(String name,
                                      String parentName,
                                      String description) {
}
