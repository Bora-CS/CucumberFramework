package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;

import static utilities.Constants.BORA_API_URL;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static utilities.Constants.BORA_API_URL;

public class WebServices {

	private static String token = null;
	private static Response addExperienceResponse = null;

	public static void login(Map<String, String> logInData) {
		RestAssured.baseURI = BORA_API_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "Application/json");
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
			throw new Exception("No token availbale, please login first!");
		}

		request.header("Content-Type", "Application/json");
		request.header("Authorization", token);

		JSONObject requestBody = new JSONObject();
		for (String key : addExperienceData.keySet()) {
			requestBody.put(key, addExperienceData.get(key));
		}
		request.body(requestBody);
		addExperienceResponse = request.post("/profile/experience");
	}

	public static void addExperienceValidation(Map<String, String> expectedResult) {
		int actualStatusCode = addExperienceResponse.getStatusCode();
		int expectedStatusCode = Integer.valueOf(expectedResult.get("StatusCode"));

		Assert.assertEquals(actualStatusCode, expectedStatusCode);

		JsonPath jp = addExperienceResponse.jsonPath();
		ArrayList<String> titles = jp.get("experience.title");

		Assert.assertTrue(titles.contains(expectedResult.get("Title")));
	}

}
