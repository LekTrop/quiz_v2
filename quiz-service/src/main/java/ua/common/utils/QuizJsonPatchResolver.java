package ua.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import ua.exception.QuizBadRequestException;

import java.util.List;

import static ua.exception.QuizErrorRegister.*;

/**
 * @author (ozhytary)
 */
@Component
public class QuizJsonPatchResolver extends JsonPatchResolver {
    private final static List<String> ALLOWED_OPERATIONS = List.of("replace");
    private final static List<String> ALLOWED_FIELDS = List.of("/name", "/description");
    private final static String PATCH_VALUE = "value";

    public QuizJsonPatchResolver(@NonNull ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void validatePatch(final JsonNode patchNode) {
        final String operation = patchNode.findPath(OPERATION).textValue();

        if (!ALLOWED_OPERATIONS.contains(operation)) {
            throw new QuizBadRequestException(JSON_PATCH_OPERATION_NOT_ALLOWED, operation, ALLOWED_OPERATIONS);
        }

        final String path = patchNode.findPath(PATH).textValue();

        if (!ALLOWED_FIELDS.contains(path)) {
            throw new QuizBadRequestException(JSON_PATCH_PATH_NOT_ALLOWED, path, ALLOWED_FIELDS);
        }

        final String value = patchNode.findPath(PATCH_VALUE).textValue();

        if(value == null){
            throw new QuizBadRequestException(ENTITY_CANNOT_BE_NULL_EXCEPTION);
        }
    }
}
