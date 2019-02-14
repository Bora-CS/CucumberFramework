package CucumberFramework;

import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.WebService2;

public class DeleteExperienceStepdefs {

	@Given("^I get the user Id by adding experience$")
	public void i_get_the_user_Id_by_adding_experience(Map<String, String>  logInData) throws Exception {
		WebService2.login(logInData);
	}

	@And("^I add a user experience$")
	public void i_add_a_user_experience(Map<String, String>  addExperienceData) throws Exception {
		WebService2.addExperience(addExperienceData);
	}

	@When("^I put in the Id and delete specific experience:$")
	public void i_put_in_the_Id_and_delete_specific_experience(Map<String, String>  deleteExperieceData) throws Exception {
		WebService2.deleteExperience(deleteExperieceData);
	}

	@Then("^I should see my profile with the updated user information$")
	public void i_should_see_my_profile_with_the_updated_user_information(Map<String, String> expectedResult) throws Exception {
		WebService2.deleteExperienceValidation(expectedResult);
	}
}
