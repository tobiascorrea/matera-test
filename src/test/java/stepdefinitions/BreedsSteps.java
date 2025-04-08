package stepdefinitions;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BreedsSteps {
    private Response response;
    private static final String BASE_URL = "https://catfact.ninja";
    private static final String DATA_KEY = "data";

    @Given("the API is up and running")
    public void apiIsUp() {
        // Verifica se a API está respondendo
        response = RestAssured.get(BASE_URL + "/breeds");
        assertThat(response.getStatusCode(), is(200));
    }

    @When("a GET request is sent to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestAssured.get(BASE_URL + endpoint);
    }

    @When("a POST request is sent to {string}")
    public void sendPostRequest(String endpoint) {
        response = RestAssured.post(BASE_URL + endpoint);
    }

    @Then("the response status should be {int}")
    public void verifyStatusCode(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }

    @Then("the response should contain a non-empty list of cat breeds")
    public void verifyResponseContent() {
        // Valida que o array 'data' não esteja vazio
        assertThat(response.jsonPath().getList(DATA_KEY), is(not(empty())));
    }

    @Then("the response should contain the error message {string}")
    public void verifyErrorMessage(String errorMessage) {
        assertThat(response.asString(), containsString(errorMessage));
    }

    @Then("the response should contain exactly {int} cat breeds")
    public void verifyNumberOfCatBreeds(int expectedCount) {
        List<?> breeds = response.jsonPath().getList(DATA_KEY);
        assertThat(breeds.size(), is(expectedCount));
    }

    @Then("the response schema should match the expected contract")
    public void validateResponseSchema() {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("breeds-schema.json"));
    }

    // Valida que os arrays estão presentes e não vazios
    @Then("the response should contain both {string} and {string} arrays which are not empty")
    public void verifyBothArraysAreNotEmpty(String arrayData, String arrayLink) {
        List<?> list1 = response.jsonPath().getList(arrayData);
        List<?> list2 = response.jsonPath().getList(arrayLink);
        assertThat("Array '" + arrayData + "' should not be empty", list1, is(not(empty())));
        assertThat("Array '" + arrayLink + "' should not be empty", list2, is(not(empty())));
    }

    // Método unificado para validar que cada objeto no array contenha as chaves esperadas (usando varargs)
    @Then("each object in the {string} array should contain the keys {string}")
    public void each_object_in_the_array_should_contain_the_keys(String arrayKey, String keys) {
        // Divide as chaves usando vírgula como separador
        String[] expectedKeys = keys.split(",\\s*");
        List<Map<String, Object>> objects = response.jsonPath().getList(arrayKey);
        for (Map<String, Object> obj : objects) {
            for (String expectedKey : expectedKeys) {
                assertThat("Expected key '" + expectedKey + "' is missing", obj, hasKey(expectedKey));
            }
        }
    }
}
