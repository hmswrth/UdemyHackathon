package com.udemy.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.udemy.qa.base.Main;

public class UdemyBusinessPage extends Main {
	// page factory - object repository

	@FindBy(id = "FirstName")
	@CacheLookup
	WebElement firstNameTextBox;

	@FindBy(id = "LastName")
	@CacheLookup
	WebElement lastNameTextBox;

	@FindBy(id = "Email")
	@CacheLookup
	WebElement emailTextBox;

	@FindBy(id = "Phone")
	@CacheLookup
	WebElement phoneNumberTextBox;

	@FindBy(id = "Company")
	@CacheLookup
	WebElement companyNameTextBox;

	@FindBy(id = "Title")
	@CacheLookup
	WebElement jobTitleTextBox;

	@FindBy(id = "Employee_Tier__c")
	@CacheLookup
	Select companySizeListBox;

	@FindBy(id = "Initial_Seat_Count__c")
	@CacheLookup
	Select peopleToBeTrainedListBox;

	@FindBy(id = "UFO_Comments__c")
	@CacheLookup
	WebElement trainingNeedsTextAreaBox;

	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement submitBtn;

	@FindBy(xpath = "//*[@class='mktoErrorMsg']")
	@CacheLookup
	WebElement errorMsg;

	// initializing page factory

	public UdemyBusinessPage() {
		PageFactory.initElements(driver, this);
	}

	// actions to be performed

	public String fillDemoRequestForm(String fname, String lname, String email, String company,
			String title, String needs) {
		firstNameTextBox.sendKeys(fname);
		lastNameTextBox.sendKeys(lname);
		emailTextBox.sendKeys(email);
		companyNameTextBox.sendKeys(company);
		jobTitleTextBox.sendKeys(title);
		trainingNeedsTextAreaBox.sendKeys(needs);

		js.executeScript("arguments[0].scrollIntoView(true)", submitBtn);
		submitBtn.click();

		js.executeScript("arguments[0].scrollIntoView(true)", emailTextBox);
		emailTextBox.click();

		js.executeScript("arguments[0].scrollIntoView(true)", errorMsg);
		return errorMsg.getText();

	}

	public void firstNameTextBox(String fName) {
		firstNameTextBox.sendKeys(fName);
	}

	public void lastNameTextBox(String lName) {
		lastNameTextBox.sendKeys(lName);
	}

	public void emailTextBox(String email) {
		emailTextBox.sendKeys(email);
	}

	public void phoneTextBox(String phone) {
		phoneNumberTextBox.sendKeys(phone);
	}

	public void companyNameTextBox(String company) {
		companyNameTextBox.sendKeys(company);
	}

	public void jobTitleTextBox(String title) {
		jobTitleTextBox.sendKeys(title);
	}

	public void needsTextBox(String needs) {
		trainingNeedsTextAreaBox.sendKeys(needs);
	}

	public void submitForm() {
		submitBtn.click();
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

}
