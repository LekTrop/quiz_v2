package ua.question.models.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;
import ua.answer.models.mapper.AnswerMapper;
import ua.common.mapper.MapperConfiguration;
import ua.question.models.Question;
import ua.question.models.QuestionDto;

/**
 * @author (ozhytary)
 */
@Mapper(config = MapperConfiguration.class, uses = AnswerMapper.class)
public interface QuestionMapper {
    QuestionDto toDto(final Question question);

    @Mapping(target = "quiz", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Question toDomain(final QuestionDto questionDto);

    @AfterMapping
    default void populateQuestion(final @MappingTarget Question question) {
        if (question != null && !CollectionUtils.isEmpty(question.getAnswers())) {
            question.getAnswers()
                    .forEach(answer -> answer.setQuestion(question));
        }
    }
}
