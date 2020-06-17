package com.udemy.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.udemy.qa.base.Main;

public class BrowserSetup extends Main {
	static String driversPath = System.getProperty("user.dir") + "/Drivers/";

	public static WebDriver setBrowser() {  //returns the specified webdriver instance
		String browserName = prop.getProperty("browser"); // retrieve browser from properties file
		if (browserName.equalsIgnoreCase("chrome")) { // set chrome driver
			System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) { // set firefox driver
			System.setProperty("webdriver.chrome.driver", driversPath + "geckodriver");
			driver = new FirefoxDriver();
		}
		return driver;
	}

}
