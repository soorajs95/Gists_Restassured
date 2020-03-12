@Create_Gists
Feature: Create_Gists

  Scenario: Create gist through API and verify the response
    Given I set the gist description as API Testing Examples
    And  I set gist public as true
    When I send a POST request for creating a gist with files
      | API_Testing.txt    | API testing is awesome   |
      | API_Automation.txt | API automation is faster |
    Then The response status should be 201 CREATED
    And The response body should match the JSON schema
    And The response should have the files
      | API_Testing.txt    |
      | API_Automation.txt |

  Scenario: Create gist without authentication through API and verify the response
    Given I set the gist description as Gist without authentication
    And  I set gist public as true
    When I send a POST request without authentication for creating a gist with files
      | API_Testing_Without_Authentication.txt | API testing is awesome |
    Then The response status should be 401 UNAUTHORIZED

  Scenario: Create gist without files through API and verify the response
    Given I set the gist description as Gist without files
    And  I set gist public as true
    When I send a POST request for creating a gist without any files
    Then The response status should be 422 UNPROCESSABLE ENTITY
