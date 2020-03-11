@Create_Gists
Feature: Create_Gists

  Scenario: Create gist through API and verify the response
    Given I set the gist description as API Testing Examples
    And  I set public as true
    When I send a POST request for creating a gist with below files
      | API_Testing.txt    | API testing is awesome   |
      | API_Automation.txt | API automation is faster |
    Then The response status should be 201 Created
    And The response should match the JSON schema