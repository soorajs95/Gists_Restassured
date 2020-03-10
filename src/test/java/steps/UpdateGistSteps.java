package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Utils;

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

    @When("I send a PATCH request for updating a gist with below files")
    public void i_send_a_patch_request_for_updating_a_gist_with_below_files(Map<String, String> gistFilesAndContent) {
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

}
