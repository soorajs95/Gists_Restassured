package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Utils;

import java.util.Map;

public class CreateGistSteps extends Utils {

    @Given("I set the gist description as {}")
    public void i_set_the_gist_description_as(String value) {
        try {
            setGistDescription(value);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to set gist description as " + value + ": " + e);
        }
    }

    @And("I set public as {}")
    public void i_set_public_as(boolean value) {
        try {
            setGistPublicField(value);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to set public as " + value + ": " + e);
        }
    }

    @When("I send a POST request for creating a gist with below files")
    public void i_send_a_POST_request_for_creating_a_gist_with_below_files(Map<String, String> gistFilesAndContent) {
        try {
            createGist(gistFilesAndContent);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send request for creating a gist: " + e);
        }
    }

    @Then("The response status should be 201 CREATED")
    public void the_response_status_should_be_created() {
        try {
            verifyResponseStatusIs201();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify response status 201 CREATED: " + e);
        }
    }

    @And("The response should match the JSON schema")
    public void the_response_should_match_the_json_schema() {
        try {
            validateJSONSchema();
        } catch (Exception e) {
            Assert.fail("ERROR: JSON schema not matched: " + e);
        }
    }

    @When("I send a POST request for creating a gist without any files")
    public void i_send_a_post_request_for_creating_a_gist_without_any_files() {
        try {
            createGistWithoutFiles();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send a post request without any files: " + e);
        }
    }

    @Then("The response status should be 422 UNPROCESSABLE ENTITY")
    public void the_response_status_should_be_unprocessable_entity() {
        try {
            verifyResponseStatusIs422();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify response status 422 UNPROCESSABLE ENTITY: " + e);
        }
    }

    @When("I send a POST request without authentication for creating a gist with below files")
    public void i_send_a_post_request_without_authentication_for_creating_a_gist_with_below_files(Map<String, String> gistFilesAndContent) {
        try {
            createGistWithoutAuthentication(gistFilesAndContent);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send post request for creating a gist without authentication: " + e);
        }
    }

    @Then("The response status should be 401 UNAUTHORIZED")
    public void the_response_status_should_be_unauthorized() {
        try {
            verifyResponseStatusIs401();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify response status 422 UNPROCESSABLE ENTITY: " + e);
        }
    }
}
