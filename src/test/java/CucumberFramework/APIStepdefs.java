package CucumberFramework;

import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.WebServices;
 

 public class APIStepdefs {

	private static String token = null;
	
	@Given("^I log in to BoraAlumniConnector$")
	public void i_log_in_to_BoraAlumniConnector(Map<String, String> logInData) throws Exception {
		token = WebServices.login(logInData);
	}

	@When("^I add experience with valid data$")
	public void i_add_experience_with_valid_data(Map<String, String> experience, String token) throws Exception {
	    WebServices.addExperience(experience, token);
	}

	@Then("^Delete the following experience profile:$")
	public void delete_the_following_experience_profile(Map<String,String> deleteProfile) throws Exception {
        WebServices.deleteExperience(deleteProfile,token );
	}

}
