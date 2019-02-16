package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cucumber.api.java.it.Data;

public class ExcelUtils {

	public static void main(String[] args) {

		try {
			String filePath = "src/test/resources/ExcelData/CreatedFromJava.xlsx";

			File file = new File(filePath);
			FileInputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.getSheet("TestData_3");
			XSSFRow row;
			XSSFCell cell;

			String[][] inputData = new String[5][3];
			inputData[0] = new String[] { "Name", "Grade", "Email Address" };
			inputData[1] = new String[] { "Jimmy", "D", "jimmy.null@gmail.com" };
			inputData[2] = new String[] { "Venus", "A", "venus.pretty@gmail.com" };
			inputData[3] = new String[] { "Shirinay", "A+++", "shirinay.impressive@gmail.com" };
			inputData[4] = new String[] { "Muradil", "A+", "murad@gmail.com" };

			for (int i = 0; i < inputData.length; i++) {
				for (int j = 0; j < inputData[i].length; j++) {
					sheet.getRow(i).getCell(j).setCellValue(inputData[i][j]);
				}
			}

			FileOutputStream os = new FileOutputStream(file);
			workbook.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static XSSFWorkbook openWorkbook(String fileName) {
		XSSFWorkbook workbook = null;
		try {
			File file = new File(Constants.EXCEL_FILES + fileName + ".xlsx");
			FileInputStream is = new FileInputStream(file);
			workbook = new XSSFWorkbook(is);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return workbook;
	}
	
	public static void saveAndCloseWorkbook (XSSFWorkbook workbook, String fileName) {
		try {
			FileOutputStream os = new FileOutputStream(Constants.EXCEL_FILES + fileName + ".xlsx");
			workbook.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createEmptySheetWithName(String fileName, String sheetName) {
		try {

			File file = new File(Constants.EXCEL_FILES + fileName + ".xlsx");
			FileInputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.createSheet(sheetName);

			FileOutputStream os = new FileOutputStream(Constants.EXCEL_FILES + fileName + ".xlsx");
			workbook.write(os);
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createSheetWithName(String filePath, String sheetName, int numberOfRows, int numberOfCols) {
		try {
			File file = new File(filePath);
			FileInputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);

			XSSFSheet sheet = workbook.createSheet(sheetName);
			XSSFRow row;

			for (int i = 0; i < numberOfRows; i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < numberOfCols; j++) {
					row.createCell(j);
				}
			}

			FileOutputStream os = new FileOutputStream(file);
			workbook.write(os);
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
