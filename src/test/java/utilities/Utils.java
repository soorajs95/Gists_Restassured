package utilities;


import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class Utils extends ServiceConstants {

    private static Response response;
    private static String descriptionField;
    private static boolean publicField;

    public void setGistDescription(String fieldValue) {
        descriptionField = fieldValue;
    }

    public void setGistPublicField(boolean fieldValue) {
        publicField = fieldValue;
    }

    public void createGist(DataTable table) {
        JSONObject fileContent = new JSONObject();
        JSONObject files = new JSONObject();
        JSONObject requestParam = new JSONObject();
        for (int i = 1; i < table.height(); i++) {
            fileContent.put("content", table.row(i).get(1));
        }
        for (int i = 1; i < table.height(); i++) {
            files.put(table.row(i).get(0), fileContent);
        }

        requestParam.put("description", descriptionField);
        requestParam.put("public", publicField);
        requestParam.put("files", files);
        System.out.println(requestParam.toJSONString());
        response = given()
                .header("Authorization", "token " + OAUTH2_TOKEN)
                .contentType("application/json")
                .body(requestParam.toJSONString())
                .post();

        response.prettyPrint();
    }

    public void verifyResponseStatusIs201() {
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }


}
