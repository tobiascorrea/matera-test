Feature: Automated Testing of GET /breeds Endpoint

  # Happy path
  Scenario: Successfully retrieve list of cat breeds
    Given the API is up and running
    When a GET request is sent to "/breeds"
    Then the response status should be 200
    And the response should contain a non-empty list of cat breeds

  # Unhappy path
  Scenario: Fail to retrieve breeds using an unsupported HTTP method
    Given the API is up and running
    When a POST request is sent to "/breeds"
    Then the response status should be 404
    And the response should contain the error message "Not Found"

  # Happy path
  Scenario: Retrieve breeds with limit parameter
    Given the API is up and running
    When a GET request is sent to "/breeds?limit=5"
    Then the response status should be 200
    And the response should contain exactly 5 cat breeds

