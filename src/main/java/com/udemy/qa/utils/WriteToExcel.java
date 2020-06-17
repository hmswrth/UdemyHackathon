package com.udemy.qa.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.udemy.qa.base.Main;

public class WriteToExcel extends Main {
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

}
