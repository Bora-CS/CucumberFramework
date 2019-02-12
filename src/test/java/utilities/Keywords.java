package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Keywords {
	private static WebDriver driver;
	
	static {
		try {
			driver = DriverFactory.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void print(Object message) {
		System.out.println(message);
	}

	public static void click(By locator) {
		driver.findElement(locator).click();
	}

	public static void input(By locator, String keysToSend) {
		driver.findElement(locator).sendKeys(keysToSend);
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static boolean searchForDataInTable(String expectedValue, By tableLocator) {
		boolean isFound = false;

		WebElement table = driver.findElement(tableLocator);
		int rowNum = table.findElements(By.tagName("tr")).size();
		int colNum = table.findElements(By.tagName("th")).size();

		for (int i = 2; i <= rowNum; i++) {
			for (int j = 1; j <= colNum; j++) {
				WebElement currentEle = table.findElement(By.xpath("//tr[" + i + "]/td[" + j + "]"));
				if (currentEle.getText().equals(expectedValue)) {
					isFound = true;
				}
			}
		}

		print("Value " + expectedValue + " found: " + isFound);
		return isFound;
	}
	
	public static String getCurrentTimeStamp() {
		Date myDate = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(myDate);
		return timeStamp;
	}
	
	public static void takeScreenShot() {
		waitFor(1);
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
		File ProjectFile = new File("target/ScreenShots/SH" + getCurrentTimeStamp()+ ".jpeg");
		try {
			FileUtils.copyFile(ScrFile, ProjectFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void dragAndDrop(By fromLocator, By toLocator) {
		WebElement from = driver.findElement(fromLocator);
		WebElement to = driver.findElement(toLocator);
		Actions act = new Actions(driver);
		act.clickAndHold(from).moveToElement(to).release().build().perform();
	}
	
	public static void hoverOverToElement(By locator) {
		WebElement elementToHover = driver.findElement(locator);
		Actions act = new Actions(driver);
		act.moveToElement(elementToHover).build().perform();
	}
	
	public static void highlightElement(By locator) {
		WebElement elementToHighlight = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", elementToHighlight);
	}
	
	public static void clickOnHiddenElement(By locator) {
		WebElement elementToClick = findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click()", elementToClick);
	}
	
	public static WebElement findElement(By locator) {
		if (Constants.DEMO_MODE) highlightElement(locator);
		return driver.findElement(locator);
	}
	
	public static void acceptAlert() {
		driver.switchTo().alert().accept();
	}
}
