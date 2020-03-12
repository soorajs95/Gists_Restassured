package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Utils;

import java.util.List;
import java.util.Map;

public class UpdateGistSteps extends Utils {

    @Given("I send a GET request for fetching random gist ID")
    public void i_send_a_get_request_for_fetching_random_gist_id() {
        try {
            getRandomGistID();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to fetch random gist ID: " + e);
        }
    }

    @When("I send a PATCH request for updating a gist with files")
    public void i_send_a_patch_request_for_updating_a_gist_with_files(Map<String, String> gistFilesAndContent) {
        try {
            updateGist(gistFilesAndContent);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send a PATCH request for updating the gist: " + e);
        }
    }

    @Then("The response status should be 200 OK")
    public void the_response_status_should_be_200_ok() {
        try {
            verifyResponseStatusIs200();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify response status 200 OK: " + e);
        }
    }

    @When("I send a PATCH request for updating a gist without any files")
    public void i_send_a_patch_request_for_updating_a_gist_without_any_files() {
        try {
            updateGistWithoutFiles();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send a post request without any files: " + e);
        }
    }

    @And("The response should not have the files")
    public void the_response_should_not_have_the_files(List<String> fileNames) {
        try {
            verifyFilesDeleted(fileNames);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify the file is deleted: " + e);
        }
    }

    @And("The response should have the files")
    public void the_response_should_have_the_files(List<String> fileNames) {
        try {
            verifyFilesCreated(fileNames);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify the file is created: " + e);
        }
    }
}
