package CucumberFramework.StepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utilities.DriverFactory;
import utilities.ExcelUtils;
import utilities.Keywords;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonDataDrivenTestStepdefs {

	private static WebDriver driver;
	private static XSSFWorkbook resultExcel;
	private static String resultSheetName;
	private static XSSFSheet resultSheet;
	private static int rowNum = 0;

	@Given("^I'm on the amazon\\.com homepage$")
	public void i_m_on_the_amazon_com_homepage() throws Exception {
		driver = DriverFactory.getInstance();
		driver.manage().deleteAllCookies();
		driver.get("https://www.amazon.com/");
		assertTrue(driver.getTitle().contains("Amazon"));
		
		resultSheetName = "AmazonTest"+Keywords.getCurrentTimeStamp();
		ExcelUtils.createEmptySheetWithName("TestResult", resultSheetName);
		resultExcel = ExcelUtils.openWorkbook("TestResult");
		resultSheet = resultExcel.getSheet(resultSheetName);
		writeDataToExcel("Title", "Sold By", "Price");
	}

	@When("^I search for an \"([^\"]*)\"$")
	public void i_search_for_an(String arg1) throws Exception {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(arg1);
		driver.findElement(By.id("twotabsearchtextbox")).submit();
		Keywords.waitFor(1);
	}

	@When("^I get some results related to my search$")
	public void i_get_some_results_related_to_my_search() throws Exception {
		System.out.println(driver.getCurrentUrl());
		String url = driver.getCurrentUrl();
		while (rowNum < 101) {
			if (url.contains("https://www.amazon.com/s/ref")) {
				scenarioSREF();
			} else if (url.contains("https://www.amazon.com/s?k")) {
				scenarioSK();
			} else {
				System.out.println("Amazon is too much for me today, try again later.");
			}
		}	

		DriverFactory.cleanUp();
	}

	private void scenarioSREF() {
		String baseXpath = "//ul[@id='s-results-list-atf']/li";
		List<WebElement> results = new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(baseXpath), 5));
		
		for (int i = 1; i <= results.size(); i++) {
			
			String title = null;
			String soldBy = null;
			String price = null;
			try {
				title = driver.findElement(By.xpath(baseXpath + "[" +  i + "]" + "//div[@class='a-row a-spacing-small']//h2")).getText();
				soldBy = driver.findElement(By.xpath(baseXpath + "[" +  i + "]" + "//div[@class='a-row a-spacing-small']//span[2]")).getText();
				
				price = driver.findElement(By.xpath(baseXpath + "[" +  i + "]" + "//span[@class='sx-price sx-price-large']")).getText();
				price = price.replace("\n", ".");
				price = price.replace(" ", "."); 
				price = price.substring(2);
			} catch (Exception e) {
				if (e.getMessage().contains("no such element")) {
					continue;
				}
			} 
			
			if (rowNum == 101) break;
			
			System.out.println("Item number " + rowNum + " - " + price);
			writeDataToExcel(title, soldBy, price);
			
		}

		driver.findElement(By.id("pagnNextString")).click();
	}

	private void scenarioSK() {
		String baseXpath = "//div[@class='s-result-list sg-row']/div";
		List<WebElement> results = new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(baseXpath), 5));
		
		for (int i = 1; i <= results.size(); i++) {
			String title = null;
			String soldBy = null;
			String price = null;
			
			try {
				title = driver.findElement(By.xpath(baseXpath + "[" +  i + "]" +  "//span[@class='a-size-medium a-color-base a-text-normal']")).getText();
				soldBy = driver.findElement(By.xpath(baseXpath + "[" +  i + "]" +  "//div[@class='a-row a-size-base a-color-secondary']")).getText();
				soldBy = soldBy.replace("by ", "");
				price = driver.findElement(By.xpath(baseXpath + "[" +  i + "]" +  "//span[@class='a-price']")).getText();
				price = price.replace("\n", ".");
//				price = price.replace(" ", "."); 
				price = price.substring(1);
			} catch (Exception e) {
				if (e.getMessage().contains("no such element")) {
					continue;
				}
			} 
			
			if (rowNum == 101) break;
			System.out.println("Item number " + rowNum + " - " + price);
			writeDataToExcel(title, soldBy, price);
		}
		
		driver.findElement(By.xpath("//ul[@class='a-pagination']/li[@class='a-last']")).click();
	}
	
	private void writeDataToExcel(String title, String soldBy, String price) {
		XSSFRow row = resultSheet.createRow(rowNum);
		row.createCell(0).setCellValue(title);
		row.createCell(1).setCellValue(soldBy);
		row.createCell(2).setCellValue(price);
		rowNum++;
	}


	@Then("^I will be able to find out the highest price, lowset price and the average price$")
	public void i_will_be_able_to_find_out_the_highest_price_lowset_price_and_the_average_price() throws Exception {
		ExcelUtils.saveAndCloseWorkbook(resultExcel, "TestResult");
		
		
	}
}
