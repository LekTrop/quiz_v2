package ua.category.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.category.models.dto.CategoryCreationRequest;
import ua.category.models.domain.Category;
import ua.category.models.dto.CategoryDto;
import ua.common.mapper.HibernateCollectionUtils;
import ua.common.mapper.MapperConfiguration;

/**
 * @author (ozhytary)
 */
@Mapper(config = MapperConfiguration.class, uses = HibernateCollectionUtils.class)
public interface CategoryMapper {
    CategoryDto toDto(final Category category);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quizzes", ignore = true)
    @Mapping(target = "parentCategory.createdAt", ignore = true)
    @Mapping(target = "parentCategory.updatedAt", ignore = true)
    @Mapping(target = "parentCategory.createdBy", ignore = true)
    @Mapping(target = "parentCategory.modifiedBy", ignore = true)
    @Mapping(target = "parentCategory.id", ignore = true)
    @Mapping(target = "parentCategory.quizzes", ignore = true)
    Category toCategory(final CategoryDto categoryDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quizzes", ignore = true)
    @Mapping(target = "parentCategory.createdAt", ignore = true)
    @Mapping(target = "parentCategory.updatedAt", ignore = true)
    @Mapping(target = "parentCategory.createdBy", ignore = true)
    @Mapping(target = "parentCategory.modifiedBy", ignore = true)
    @Mapping(target = "parentCategory.id", ignore = true)
    @Mapping(target = "parentCategory.quizzes", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "childCategories", ignore = true)
    Category toDomain(final CategoryCreationRequest categoryCreationRequest);
}
