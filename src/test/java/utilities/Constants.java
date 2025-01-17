package utilities;

public class Constants {
	public static final String CHROME_DRIVER_PATH_MAC = "src/test/resources/Drivers/chromedriver";
	public static final String CHROME_DRIVER_PATH_WINDOWS = "src/test/resources/Drivers/chromedriver.exe";
	public static final String PROJECT_PATH = System.getProperty("user.dir").replaceAll("\\\\", "/"); // Raiden
	public static final String BORA_FORM_URL =  "file:///" + PROJECT_PATH + "/src/main/resources/MuradilE/index.html"; // Raiden
	public static final String BORA_STUDENTS_URL =  "file:///" + PROJECT_PATH + "/src/main/resources/MuradilE/students.html";
	public static final String BORA_API_URL = "http://ec2-54-243-3-145.compute-1.amazonaws.com:5000/api/";
	public static final String EXCEL_FILES = "src/test/resources/ExcelData/";
	public static final boolean DEMO_MODE = false;
}
