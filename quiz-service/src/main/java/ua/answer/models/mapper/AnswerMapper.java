package ua.answer.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.answer.models.Answer;
import ua.answer.models.AnswerDto;
import ua.common.mapper.HibernateCollectionUtils;
import ua.common.mapper.MapperConfiguration;

/**
 * @author (ozhytary)
 */
@Mapper(config = MapperConfiguration.class, uses = HibernateCollectionUtils.class)
public interface AnswerMapper {
    @Mapping(target = "answerId", ignore = true)
    @Mapping(target = "question",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Answer toDomain(final AnswerDto answerDto);

    AnswerDto toDto(final Answer answer);
}
