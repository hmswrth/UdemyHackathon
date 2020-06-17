package com.udemy.qa.pages;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.udemy.qa.base.Main;

public class HomePage extends Main {
	
	//PageFactory - ObjectRepository
	
	@FindBy(xpath="//input[@placeholder='Search for anything']") //search text box
	WebElement searchBox;
	
	@FindBy(xpath="(//button[@type='submit'])[1]") //search button
	WebElement searchBtn;
	
	@FindBy(xpath="(//a[contains(text(),'Udemy for Business')])[1]") //nav link
	@CacheLookup
	WebElement udemyForBusiness;
	
	//Initialize the page objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public SearchResultsPage search(String testData) {
		searchBox.sendKeys(testData);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		searchBtn.click();
		return new SearchResultsPage();
	}
	
	public UdemyBusinessPage udemyForBusiness() {
		String parentWindowHandle = driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, IMPLICIT_WAIT);
		wait.until(ExpectedConditions.visibilityOf(udemyForBusiness));
		udemyForBusiness.click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for (String handle : allWindowHandles) {
			if(!handle.contentEquals(parentWindowHandle)) {
				driver.switchTo().window(handle);
			}
		}
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
	
	return new UdemyBusinessPage();
	}

}
