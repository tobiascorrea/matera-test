package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CatFactsClient {
    private static final String BASE_URL = "https://catfact.ninja";

    public Response get(String endpoint) {
        return RestAssured.get(BASE_URL + endpoint);
    }

    public Response post(String endpoint) {
        return RestAssured.post(BASE_URL + endpoint);
    }

    public void validateSchema(Response response, String schemaPath) {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }
}
