package ua.quiz.models.mapper;

import org.mapstruct.*;
import org.springframework.util.CollectionUtils;
import ua.category.models.mapper.CategoryMapper;
import ua.common.mapper.HibernateCollectionUtils;
import ua.common.mapper.MapperConfiguration;
import ua.question.models.mapper.QuestionMapper;
import ua.quiz.models.domain.Quiz;
import ua.quiz.models.dto.QuizCreationRequest;
import ua.quiz.models.dto.QuizDto;

/**
 * @author (ozhytary)
 */
@Mapper(config = MapperConfiguration.class,
        uses = {QuestionMapper.class, CategoryMapper.class, HibernateCollectionUtils.class}
)
public interface QuizMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "quizId", ignore = true)
    @Mapping(target = "quizStatus", ignore = true)
    @Mapping(target = "category", ignore = true)
    Quiz toDomain(final QuizCreationRequest quizCreationRequest);
    
    QuizDto toDto(final Quiz quiz);

    @AfterMapping
    default void populateRelationships(final @MappingTarget Quiz quiz) {
        if (quiz != null && !CollectionUtils.isEmpty(quiz.getQuestions())) {
            quiz.getQuestions()
                .forEach(question -> question.setQuiz(quiz));
        }
    }
}
