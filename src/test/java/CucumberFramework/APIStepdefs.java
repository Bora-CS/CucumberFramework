package CucumberFramework;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utilities.WebServices;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class APIStepdefs {
	@Given("^I log in to BoraAlumniConnector$")
	public void i_log_in_to_BoraAlumniConnector(Map<String, String> logInData) throws Exception {
		WebServices.login(logInData);
	}

	@When("^I add an experience with valid data$")
	public void i_add_an_experience_with_valid_data(Map<String, String> experience) throws Exception {
		WebServices.addExperience(experience);
	}

	@Then("^I should see my profile with the updated experience information$")
	public void i_should_see_my_profile_with_the_updated_experience_information(Map<String, String> expectedResult)
			throws Exception {
		WebServices.addExperienceValidation(expectedResult);
	}
}
