package CucumberFramework;

import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.ApiKey;
import utilities.ApiValidate;

public class ApiCreateAPost {
	
	private static String token = null;
	private static Map<String, String> actualResult;
	@Given("^I log in$")
	public void i_log_in(Map<String, String> loginData) throws Exception {
		
	   token = ApiKey.Login(loginData);
	 
	}

	@When("^I create a post with valid data$")
	public void i_create_a_post_with_valid_data(Map<String, String> text) throws Exception {
		actualResult = ApiKey.CreatAPost(token, text);
	    
	}

	@Then("^I should see response body with correct data$")
	public void i_should_see_response_body_with_correct_data(Map<String, String> expectedResult) throws Exception {
		System.out.println(actualResult);
		System.out.println(expectedResult);
	ApiValidate.validateCreateAPost(actualResult, expectedResult);
	}
}
