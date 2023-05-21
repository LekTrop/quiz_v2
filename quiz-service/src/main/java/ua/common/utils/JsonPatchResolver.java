package ua.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.exception.QuizBadRequestException;
import ua.exception.QuizErrorRegister;

import java.io.IOException;

/**
 * @author (ozhytary)
 */
@Component
@Getter
@RequiredArgsConstructor
public abstract class JsonPatchResolver {
    protected final static String OPERATION = "op";
    protected final static String PATH = "path";
    protected final static String VALUE = "value";

    @NonNull
    private final ObjectMapper objectMapper;

    public <T> T applyPatch(final JsonPatch patch, final T entity) throws QuizBadRequestException {
        validatePatch(getObjectMapper().valueToTree(patch));
        final JsonNode entityJsonNode = objectMapper.valueToTree(entity);
        final JsonNode patchedJsonNode = mergeEntities(patch, entityJsonNode);
         return restoreEntity(entity, patchedJsonNode);
    }

    private JsonNode mergeEntities(final JsonPatch patch, final JsonNode jsonNode) {
        try {
            return patch.apply(jsonNode);
        } catch (JsonPatchException e) {
            throw new QuizBadRequestException(
                    QuizErrorRegister.JSON_PATCH_CANNOT_BE_APPLIED, e.getLocalizedMessage(), patch);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T restoreEntity(final T entity, final JsonNode patched) {
        final Class<?> entityClass = entity.getClass();

        try {
            return (T) objectMapper.readValue(patched.toString(), entityClass);
        } catch (IOException e) {
            throw new QuizBadRequestException(
                    QuizErrorRegister.JSON_PATCH_CANNOT_BE_RESTORED,
                    entityClass.getSimpleName(),
                    e.getLocalizedMessage());
        }
    }

    protected abstract void validatePatch(final JsonNode patchNode);
}
