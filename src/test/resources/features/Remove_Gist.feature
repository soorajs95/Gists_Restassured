@Delete_Gist
Feature: Delete_Gist

  Scenario: Delete gist through API and verify the response
    Given I send a GET request with user soorajs95 for fetching random gist ID
    When I send a DELETE request for deleting a gist
    Then The response status should be 204 NO CONTENT