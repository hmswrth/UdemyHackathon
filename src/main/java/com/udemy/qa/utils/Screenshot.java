package com.udemy.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.udemy.qa.base.Main;

public class Screenshot extends Main{
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
