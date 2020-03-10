package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Utils;

public class DeleteGistSteps extends Utils {

    @When("I send a DELETE request for deleting a gist")
    public void i_send_a_delete_request_for_deleting_a_gist() {
        try {
            deleteGist();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send DELETE request: " + e);
        }
    }

    @Then("The response status should be 204 NO CONTENT")
    public void the_response_status_should_be_204_no_content() {
        try {
            verifyResponseStatusIs204();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify response status 204 NO CONTENT: " + e);
        }
    }

}
