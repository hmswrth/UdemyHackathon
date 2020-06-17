package com.udemy.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.udemy.qa.utils.EventListener;
import com.udemy.qa.utils.TestUtil;

public class Main {
	
	public static WebDriver driver;
	public static Properties prop;
	public static JavascriptExecutor js;
	public  static EventFiringWebDriver e_driver;
	public static EventListener eventListener;
	
	public Main() {
		try {
			prop = new Properties(); // Used to Instantiate properties
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir")+"/src/main/java/com/udemy/qa/config/config.properties"); // providing the path of properties file
			prop.load(file);
		} catch (IOException e) {
			System.out.println("Error while reading the config file");
			e.printStackTrace();
		}
	}
	
	public static void init() {
		String driversPath = System.getProperty("user.dir")+"/Drivers/";
		
		String browserName = prop.getProperty("browser"); // retrieve browser from properties file
		if (browserName.equalsIgnoreCase("chrome")) { //set chrome driver
			System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) { //set firefox driver
			System.setProperty("webdriver.chrome.driver", driversPath + "geckodriver");
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListenerHandler to register it with EventFiringWebDriver
		eventListener = new EventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		js = (JavascriptExecutor) driver;
		
		
	}

}
