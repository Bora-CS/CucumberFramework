package utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiKey {

	public static String Login(Map<String, String> loginData) {
		
		RestAssured.baseURI = "http://ec2-54-243-3-145.compute-1.amazonaws.com:5000";
		
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		
		JSONObject requestBody = new JSONObject();
		
		
		for(String key:loginData.keySet()) {
		requestBody.put(key, loginData.get(key));
	
			}
		
		
		
		request.body(requestBody);
	
		Response response = request.post("/api/users/login");
		
		JsonPath jP = response.jsonPath();
		
		HashMap<String, String> loginBody = jP.get("");
		
		String token = loginBody.get("token");
		
		return token;
		
	}
	
	
	public static Map addExperience(String token, Map<String, String> data) {
RestAssured.baseURI = "http://ec2-54-243-3-145.compute-1.amazonaws.com:5000";
		
		RequestSpecification request = RestAssured.given();
		
		
		request.header("Authorization", token);
		request.header("Content-Type", "application/json");
		
		JSONObject requestBody = new JSONObject();
		
		for(String key:data.keySet()) {
			
		
		requestBody.put(key, data.get(key));
		
		}
		request.body(requestBody);
	
		Response response = request.post("/api/profile/experience");
		
		int statusCode = response.getStatusCode();
		
		JsonPath jP = response.jsonPath();
		
		Map<String, String> experienceBody = jP.get("");
		
		return experienceBody;
	}
	
	public static Map<String, String> CreatAPost(String token, Map<String, String> data){
		RestAssured.baseURI = "http://ec2-54-243-3-145.compute-1.amazonaws.com:5000";
        
        RequestSpecification request = RestAssured.given();
        
        request.header("Authorization", token);
        
        request.header("Content-Type", "application/json");
        
        JSONObject requestBody = new JSONObject();
        
        for(String key:data.keySet()) {
        requestBody.put(key, data.get(key));
        }
        System.out.println(requestBody);
        request.body(requestBody);
        
        Response response = request.post("/api/posts");
        
        String statusCode = String.valueOf(response.getStatusCode());
        
        JsonPath jp = response.jsonPath();
        
        String text = jp.get("text");
        
        Map<String, String> actualResult = new HashMap<String, String>();
        
        actualResult.put("status code", statusCode);
        actualResult.put("text", text);
        return actualResult;
	}
	
}
