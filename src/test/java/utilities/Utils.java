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
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Utils extends ServiceConstants {

    static Response response;
    static JSONObject gistsRequestObject = new JSONObject();
    static ArrayList<String> gistIds;
    static String randomGistId;

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
            fileContent.put("content", v);
            files.put(k, fileContent);
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
        gistIds = given()
                .get("users/soorajs95/gists").path("id");
        int index = (int) (Math.random() * gistIds.size());
        randomGistId = gistIds.get(index);
    }

    public void createGist(Map<String, String> gistFilesAndContent) {
        formRequestBody(gistFilesAndContent);
        response = formRequestSpec()
                .post("gists");
    }

    public void updateGist(Map<String, String> gistFilesAndContent) {
        formRequestBody(gistFilesAndContent);
        response = formRequestSpec()
                .patch("gists/" + randomGistId);
    }

    public void deleteGist() {
        response = given()
                .header("Authorization", "token " + OAUTH2_TOKEN)
                .delete("gists/" + randomGistId);
    }

    public void verifyResponseStatusIs200() {
        Assert.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
    }

    public void verifyResponseStatusIs201() {
        Assert.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }

    public void verifyResponseStatusIs204() {
        Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, response.statusCode());
    }

    public void validateJSONSchema(String jsonSchemaName) {
        try {
            FileReader reader = new FileReader("src/test/resources/schema/" + jsonSchemaName + "Gist.json");
            JSONObject jsonSchema = new JSONObject(
                    new JSONTokener(reader));
            JSONObject jsonSubject = new JSONObject(
                    new JSONTokener(response.asInputStream()));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
