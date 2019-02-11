package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	private static WebDriver driver = null;

	private DriverFactory() {
	}

	public static WebDriver getInstance() throws Exception {
		if (driver == null) {
			String driverPath = determineOSandGetDriverPath();
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		return driver;
	}
	
	public static void cleanUp() {
		driver = null;
	}

	private static String determineOSandGetDriverPath() throws Exception {
		String os = System.getProperty("os.name");
		System.out.println("Operating System: " + os);
		if (os.toLowerCase().startsWith("mac")) {
			return Constants.CHROME_DRIVER_PATH_MAC;
		} else if (os.toLowerCase().startsWith("windows")) {
			return Constants.CHROME_DRIVER_PATH_WINDOWS;
		} else {
			throw new Exception("Unknown Operating System: " + os);
		}
	}

}
