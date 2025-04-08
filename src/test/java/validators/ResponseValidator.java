package validators;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class ResponseValidator {
    private static final String DATA_KEY = "data";

    public void validateStatusCode(Response response, int expectedStatus) {
        assertThat(response.getStatusCode(), is(expectedStatus));
    }

    public void validateNonEmptyData(Response response) {
        assertThat(response.jsonPath().getList(DATA_KEY), is(not(empty())));
    }

    public void validateExactNumberOfCatBreeds(Response response, int expectedCount) {
        List<?> breeds = response.jsonPath().getList(DATA_KEY);
        assertThat(breeds.size(), is(expectedCount));
    }

    public void validateResponseContainsErrorMessage(Response response, String errorMessage) {
        assertThat(response.asString(), containsString(errorMessage));
    }

    public void validateNonEmptyArrays(Response response, String array1Key, String array2Key) {
        List<?> list1 = response.jsonPath().getList(array1Key);
        List<?> list2 = response.jsonPath().getList(array2Key);
        assertThat("Array '" + array1Key + "' should not be empty", list1, is(not(empty())));
        assertThat("Array '" + array2Key + "' should not be empty", list2, is(not(empty())));
    }

    public void validateEachObjectContainsKeys(Response response, String arrayKey, String keys) {
        String[] expectedKeys = keys.split(",\\s*");
        List<Map<String, Object>> objects = response.jsonPath().getList(arrayKey);
        for (Map<String, Object> obj : objects) {
            for (String key : expectedKeys) {
                assertThat("Expected key '" + key + "' is missing", obj, hasKey(key));
            }
        }
    }
}
