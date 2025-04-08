package stepdefinitions;

import clients.CatFactsClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import validators.ResponseValidator;

public class BreedsSteps {
    private Response response;
    private CatFactsClient client = new CatFactsClient();
    private ResponseValidator validator = new ResponseValidator();

    @Given("the API is up and running")
    public void apiIsUp() {
        response = client.get("/breeds");
        validator.validateStatusCode(response, 200);
    }

    @When("a GET request is sent to {string}")
    public void sendGetRequest(String endpoint) {
        response = client.get(endpoint);
    }

    @When("a POST request is sent to {string}")
    public void sendPostRequest(String endpoint) {
        response = client.post(endpoint);
    }

    @Then("the response status should be {int}")
    public void verifyStatusCode(int statusCode) {
        validator.validateStatusCode(response, statusCode);
    }

    @Then("the response should contain a non-empty list of cat breeds")
    public void verifyResponseContent() {
        validator.validateNonEmptyData(response);
    }

    @Then("the response should contain the error message {string}")
    public void verifyErrorMessage(String errorMessage) {
        validator.validateResponseContainsErrorMessage(response, errorMessage);
    }

    @Then("the response should contain exactly {int} cat breeds")
    public void verifyNumberOfCatBreeds(int expectedCount) {
        validator.validateExactNumberOfCatBreeds(response, expectedCount);
    }

    @Then("the response schema should match the expected contract")
    public void validateResponseSchema() {
        client.validateSchema(response, "breeds-schema.json");
    }

    @Then("the response should contain both {string} and {string} arrays which are not empty")
    public void verifyBothArraysAreNotEmpty(String arrayData, String arrayLink) {
        validator.validateNonEmptyArrays(response, arrayData, arrayLink);
    }

    @Then("each object in the {string} array should contain the keys {string}")
    public void eachObjectInTheArrayShouldContainTheKeys(String arrayKey, String keys) {
        validator.validateEachObjectContainsKeys(response, arrayKey, keys);
    }
}
