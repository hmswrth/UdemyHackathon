package com.udemy.qa.utils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.udemy.qa.base.Main;

public class TestUtil extends Main{
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	static String filePath = System.getProperty("user.dir") + "/src/main/java/com/udemy/qa/testdata/UdemyTestData.xlsx";
	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {  //read test data from excel
		FileInputStream file = null;
		try {
			file = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	public static void writeResultsToExcel(Map<Integer, Object[]> dataset) { // write test result to the excel file
		// Blank workbook
		XSSFWorkbook sample = new XSSFWorkbook();

		// Blank sheet
		XSSFSheet sheet = sample.createSheet("results");

		Set<Integer> set = dataset.keySet();
		int rowNo = 0;
		for (Integer key : set) {
			Row row = sheet.createRow(rowNo++);
			Object[] data = dataset.get(key);
			int cellno = 0;
			for (Object value : data) {
				Cell cell = row.createCell(cellno++);

				if (value instanceof String)
					cell.setCellValue((String) value);
				else if (value instanceof Integer)
					cell.setCellValue((Integer) value);

			}

		}
		FileOutputStream writefile;
		try {
			writefile = new FileOutputStream("results.xlsx");
			System.out.println("exporting results to excel...");
			sample.write(writefile);
			writefile.close();
			sample.close();
			System.out.println("results exported successfully!");
		} catch (IOException e) {
			System.out.println("Error occured while writing excel file");
			e.printStackTrace();
		}
	}
	
	//method to capture screenshot
	public static void takeAScreenshot() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // Create object of SimpleDateFormat class and decide the format
		Date date_now = new Date(); //get current date time with Date()
		String date = dateFormat.format(date_now); //format date_now to the liking
		
		TakesScreenshot scrShot = (TakesScreenshot) driver;  //instantiate screenshot object
		File Src = scrShot.getScreenshotAs(OutputType.FILE); //take the screenshot
		String filePath = System.getProperty("user.dir") + "/Snapshots/screenshot_"+date+".png"; //set location for output file
		File Dest = new File(filePath); //create a output file object
		try {
			FileUtils.copyFile(Src, Dest); //update output file with screenshot taken
		} catch (IOException e) {
			System.out.println("Error occured while copying screenshot file");
			e.printStackTrace();
		} 

	}
}
