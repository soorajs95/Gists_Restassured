package steps;


import io.cucumber.java.Before;
import io.restassured.RestAssured;
import utilities.ServiceConstants;

public class Hooks {

    @Before
    public void setUp() {
        RestAssured.baseURI = ServiceConstants.BASE_URI;
    }
}
