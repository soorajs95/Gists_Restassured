package utilities;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Utils extends ServiceConstants {

    private static Response response;
    private static String randomGistId;
    public static JSONObject gistsRequestObject;

    public void setGistDescription(String fieldValue) {
        gistsRequestObject.put("description", fieldValue);
    }

    public void setGistPublicField(boolean fieldValue) {
        gistsRequestObject.put("public", fieldValue);
    }

    public void formRequestBody(Map<String, String> gistFilesAndContent) {
        JSONObject files = new JSONObject();
        gistFilesAndContent.forEach((k, v) -> {
            JSONObject fileContent = new JSONObject();
            if (v.equalsIgnoreCase("null")) {
                files.put(k, JSONObject.NULL);
            } else {
                fileContent.put("content", v);
                files.put(k, fileContent);
            }
        });
        gistsRequestObject.put("files", files);
    }

    public RequestSpecification formRequestSpec() {
        return given()
                .header("Authorization", "token " + OAUTH2_TOKEN)
                .contentType("application/json")
                .body(gistsRequestObject.toString());
    }

    public void getRandomGistID() {
        ArrayList<String> gistIds = given()
                .get("users/" + USER_NAME + "/gists").path("id");
        int index = (int) (Math.random() * gistIds.size());
        randomGistId = gistIds.get(index);
    }

    public void createGist(Map<String, String> gistFilesAndContent) {
        formRequestBody(gistFilesAndContent);
        response = formRequestSpec()
                .post(GISTS_URL);
    }

    public void createGistWithoutFiles() {
        response = formRequestSpec()
                .post(GISTS_URL);
    }

    public void createGistWithoutAuthentication(Map<String, String> gistFilesAndContent) {
        formRequestBody(gistFilesAndContent);
        response = given()
                .contentType("application/json")
                .body(gistsRequestObject.toString())
                .post(GISTS_URL);
    }

    public void updateGist(Map<String, String> gistFilesAndContent) {
        formRequestBody(gistFilesAndContent);
        response = formRequestSpec()
                .patch(GISTS_URL + "/" + randomGistId);
    }

    public void updateGistWithoutFiles() {
        response = formRequestSpec()
                .patch(GISTS_URL + "/" + randomGistId);
    }

    public void verifyFilesCreated(List<String> fileNames) {
        for (String fileName : fileNames) {
            Assert.assertTrue("Filename not found in response body", response.path("files").toString().contains(fileName));
        }
    }

    public void verifyFilesDeleted(List<String> fileNames) {
        for (String fileName : fileNames) {
            Assert.assertFalse("Filename found in response body", response.path("files").toString().contains(fileName));
        }
    }

    public void deleteGist() {
        response = given()
                .header("Authorization", "token " + OAUTH2_TOKEN)
                .delete(GISTS_URL + "/" + randomGistId);
    }

    public void verifyResponseStatusIs200() {
        Assert.assertEquals("Status code did not match", HttpURLConnection.HTTP_OK, response.statusCode());
    }

    public void verifyResponseStatusIs201() {
        Assert.assertEquals("Status code did not match", HttpURLConnection.HTTP_CREATED, response.statusCode());
    }

    public void verifyResponseStatusIs204() {
        Assert.assertEquals("Status code did not match", HttpURLConnection.HTTP_NO_CONTENT, response.statusCode());
    }

    public void verifyResponseStatusIs401() {
        Assert.assertEquals("Status code did not match", HttpURLConnection.HTTP_UNAUTHORIZED, response.statusCode());
    }

    public void verifyResponseStatusIs422() {
        Assert.assertEquals("Status code did not match", 422, response.statusCode());
    }

    public void validateJSONSchema() {
        try {
            FileReader reader = new FileReader("src/test/resources/schema/createGist.json");
            JSONObject jsonSchema = new JSONObject(
                    new JSONTokener(reader));
            JSONObject jsonSubject = new JSONObject(
                    new JSONTokener(response.asString()));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
