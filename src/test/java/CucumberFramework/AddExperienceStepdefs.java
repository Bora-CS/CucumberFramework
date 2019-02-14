package CucumberFramework;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.WebServices;

public class AddExperienceStepdefs {

	
	
	@Given("^I log in to BoraAlumniConnector$")
	public void i_log_in_to_BoraAlumniConnector(Map<String, String> logInData) throws Exception {
		WebServices.login(logInData);
	}

	@When("^I add experience with valid data$")
	public void i_add_an_experience_with_valid_data(Map<String, String> addExperienceData) throws Exception {
		WebServices.addExperience(addExperienceData);
	}

	@Then("^I should see my profile with the updated experience information$")
	public void i_should_see_my_profile_with_the_updated_experience_information(Map<String, String> expectedResult)
			throws Exception {
		WebServices.addExperienceValidation(expectedResult);
	}

}
