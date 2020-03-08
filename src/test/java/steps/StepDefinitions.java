package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Utils;

public class StepDefinitions extends Utils {

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
    public void i_send_a_POST_request_for_creating_a_gist_with_below_files(DataTable table) {
        try {
            createGist(table);
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to send request for creating a gist: " + e);
        }
    }

    @Then("The response status should be 201 Created")
    public void the_response_status_should_be_Created() {
        try {
            verifyResponseStatusIs201();
        } catch (Exception e) {
            Assert.fail("ERROR: Failed to verify response: " + e);
        }
    }
}
