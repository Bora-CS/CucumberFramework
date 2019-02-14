package CucumberFramework;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utilities.DriverFactory;
import utilities.Keywords;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EtsyUIStepdefs {
	public static WebDriver driver;
	

	@Before
	public void setUpUITest() throws Exception {
		driver = DriverFactory.getInstance();
	}

	@Given("^I navigate to Etsy\\.com homepage$")
	public void i_navigate_to_Etsy_com_homepage() throws Exception {
		driver.get("https://www.etsy.com/");
		assertTrue(driver.getTitle().contains("Etsy"));
	}

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String item) throws Exception {
		driver.findElement(By.id("search-query")).sendKeys(item);
		driver.findElement(By.id("search-query")).submit();
	}

	@Then("^I should see results related to my search$")
	public void i_should_see_results_related_to_my_search() throws Exception {
		List<WebElement> cards = driver.findElements(By.cssSelector(".v2-listing-card__info > div > h2"));
		System.out.println(cards.size());
		for (WebElement card : cards) {
			System.out.println("Checking if title contails iPhone: " + card.getText() );
			assertTrue(card.getText().toLowerCase().contains("phone"));
		}
	}

	@After
	public void cleanUp() {
		driver.close();
		driver.quit();
		Keywords.waitFor(1);
		DriverFactory.cleanUp();
	}

}
