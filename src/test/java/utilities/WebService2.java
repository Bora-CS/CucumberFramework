package utilities;

import static utilities.Constants.BORA_API_URL;

import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebService2 {

	public static String token = null;
	public static Response addExperienceResponse = null;
	public static Response deleteExperienceResponse = null;

	public static void login(Map<String, String> logInData) {
		RestAssured.baseURI = BORA_API_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		JSONObject requestBody = new JSONObject();
		for (String key : logInData.keySet()) {
			requestBody.put(key, logInData.get(key));
		}
		request.body(requestBody);

		Response response = request.post("users/login");
		Assert.assertEquals(200, response.getStatusCode());

		JsonPath jp = response.jsonPath();
		token = jp.get("token");
		
	}

	public static void addExperience(Map<String, String> addExperienceData) throws Exception {
		RestAssured.baseURI = BORA_API_URL;
		RequestSpecification request = RestAssured.given();

		if (token == null) {
			throw new Exception("No token availbale!");
		}

		request.header("Content-Type", "application/json");
		request.header("Authorization", token);

		JSONObject requestBody = new JSONObject();
		for (String key : addExperienceData.keySet()) {
			requestBody.put(key, addExperienceData.get(key));
		}
		request.body(requestBody);
		addExperienceResponse = request.post("/profile/experience");
	}

	public static void deleteExperience(Map<String, String> deleteExperieceData) {
		RestAssured.baseURI = BORA_API_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("Authorization", token);

		JSONObject requestBody = new JSONObject();
		for (String key : deleteExperieceData.keySet()) {
			requestBody.put(key, deleteExperieceData.get(key));
		}
		request.body(requestBody);
		JsonPath jp = addExperienceResponse.jsonPath();
		ArrayList<String> iDs = jp.get("experience._id");
		deleteExperienceResponse = request.delete("/profile/experience/" + iDs.get(0));

	}

	public static void deleteExperienceValidation(Map<String, String> expectedResult) {
		int actualStatusCode = deleteExperienceResponse.getStatusCode();
		int expectedStatusCode = Integer.valueOf(expectedResult.get("StatusCode"));

		Assert.assertEquals(actualStatusCode, expectedStatusCode);

	}

}
