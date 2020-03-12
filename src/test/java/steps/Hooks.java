package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.json.JSONObject;
import utilities.ServiceConstants;
import utilities.Utils;

public class Hooks {

    @Before
    public void setUp() {
        RestAssured.baseURI = ServiceConstants.BASE_URI;
        Utils.gistsRequestObject = new JSONObject();
    }

    @After
    public void tearDown() {
        System.gc();
    }

}
