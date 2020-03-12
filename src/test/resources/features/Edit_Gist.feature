@Update_Gist
Feature: Update_Gist

  Scenario: Update gist through API and verify the response
    Given I send a GET request with username soorajs95 for fetching random gist ID
    And I set the gist description as API Testing Updating Examples
    And  I set gist public as true
    When I send a PATCH request for updating a gist with files
      | API_Testing.txt    | API testing is updated and awesome |
      | API_Automation.txt | null                               |
      | README.md          | Read me file                       |
    Then The response status should be 200 OK
    And The response body should match the JSON schema
    And The response should have the files
      | README.md |
    And The response should not have the files
      | API_Automation.txt |

  Scenario: Update gist without file through API and verify the response
    Given I send a GET request with username soorajs95 for fetching random gist ID
    And I set the gist description as API Testing Updating Examples
    And  I set gist public as true
    When I send a PATCH request for updating a gist without any files
    Then The response status should be 200 OK
