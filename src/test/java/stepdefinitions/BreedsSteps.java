package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BreedsSteps {
    private Response response;
    private final String BASE_URL = "https://catfact.ninja";

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
        // Supondo que a resposta tem um campo 'data' com a lista
        assertThat(response.jsonPath().getList("data"), is(not(empty())));
    }

    @Then("the response should contain the error message {string}")
    public void verifyErrorMessage(String errorMessage) {
        assertThat(response.asString(), containsString(errorMessage));
    }
}
