@Update_Gist
Feature: Update_Gist

  Scenario: Update gist through API and verify the response
    Given I send a GET request for fetching random gist ID
    And I set the gist description as API Testing Updating Examples
    And  I set public as true
    When I send a PATCH request for updating a gist with below files
      | API_Testing.txt    | API testing is updated and awesome   |
      | API_Automation.txt | API automation is updated and faster |
    Then The response status should be 200 OK
    And The response should match the update JSON schema