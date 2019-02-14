package utilities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class WebServices {

	public static final String APPLICATION_URL = "http://ec2-54-243-3-145.compute-1.amazonaws.com:5000/api/";

	public static String login(Map<String, String> logInData) {

		RestAssured.baseURI = APPLICATION_URL;

		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		JSONObject requestBody = new JSONObject();
		System.out.println(logInData);
		for (String key : logInData.keySet()) {

			requestBody.put(key, logInData.get(key));
		}
		request.body(requestBody);
		Response response = request.post("users/login");
		int statusCode = response.getStatusCode();

		JsonPath jp = response.jsonPath();

		String token = jp.get("token");

		return token;
	}

	public static Map<String, String> addExperience(Map<String, String> experience, String token) throws Exception {

		RestAssured.baseURI = APPLICATION_URL;

		RequestSpecification request = RestAssured.given();
		if (token == null) {
			throw new Exception("No token availbale, please login first!");
		}
		request.header("Content-Type", "application/json");
		request.header("Authorization", token);
		JSONObject requestBody = new JSONObject();

		for (String key : experience.keySet()) {
			requestBody.put(key, experience.get(key));
		}
		request.body(requestBody);
		Response response = request.post("/api/profile/experience");

		int statusCode = response.getStatusCode();
		JsonPath jp = response.jsonPath();

		Map<String, String> experienceData = jp.get("");
		return experienceData;
	}


	public static void deleteExperience(Map<String, String> deleteProfile, String token) {

		RestAssured.baseURI = APPLICATION_URL;

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);

		Response response = request.delete("profile/experience/5c55cefd3342c205c707b43f");
		JSONObject requestBody = new JSONObject();
		request.body(requestBody);
		int statusCode = response.getStatusCode();
		System.out.println(response.getBody().asString());
		Assert.assertEquals(statusCode, 200);

	}

}
