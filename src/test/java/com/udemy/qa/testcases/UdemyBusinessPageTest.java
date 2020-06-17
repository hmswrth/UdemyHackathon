package com.udemy.qa.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;

import com.udemy.qa.base.Main;
import com.udemy.qa.pages.HomePage;
import com.udemy.qa.pages.UdemyBusinessPage;
import com.udemy.qa.utils.TestUtil;

public class UdemyBusinessPageTest extends Main{
	static HomePage homePage;
	static UdemyBusinessPage udemyBusinessPage;
	
	public UdemyBusinessPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		init();
		homePage = new HomePage();
		udemyBusinessPage = homePage.udemyForBusiness();	
	}
	
	@DataProvider
	public Object[][] udemyTestData() {
		return TestUtil.getTestData("UdemyBusiness");
		
	}
	@Test(priority = 1, dataProvider = "udemyTestData")
	public void udemyBusinessDemoFormTest(String fname, String lname, String email, String company,
			String title, String needs) {
		String errorMsg = udemyBusinessPage.fillDemoRequestForm(fname, lname, email, company, title, needs);
		Assert.assertEquals(errorMsg, "Must be valid company email.\n" + 
				"example@yourdomain.com" , "error while verifying email");
	}  
	
/*	@Test(priority = 2)
	public void firstNameTextBoxTest() {
		udemyBusinessPage.firstNameTextBox("breakers");
	}
	
	@Test(priority = 3)
	public void lastNameTextBoxTest() {
		udemyBusinessPage.lastNameTextBox("zip");
	}
	
	@Test(priority = 4)
	public void emailTextBoxTest() {
		udemyBusinessPage.emailTextBox("breakers.zip");
	}
	
	@Test(priority = 5)
	public void phoneNumberTextBoxTest() {
		udemyBusinessPage.phoneTextBox("123456789");
	}
	
	@Test(priority = 6)
	public void companyNameTextBoxTest() {
		udemyBusinessPage.companyNameTextBox("breakers.zip");
	}
	
	@Test(priority = 7)
	public void jobTitleTextBoxTest() {
		udemyBusinessPage.jobTitleTextBox("Talent Manager");
	}
	
	@Test(priority = 8)
	public void businessNeedsTextBoxTest() {
		udemyBusinessPage.needsTextBox("Empowering individuals to write efficient and bug-free code");
	}
	
	@Test(priority = 9)
	public void submitFormTest() {
		udemyBusinessPage.submitForm();
	}
	
	@Test(priority = 10)
	public void emailErrorMessageTest() {
		String msg = udemyBusinessPage.getErrorMsg();
		Assert.assertEquals(msg, "Must be valid company email.\n" + 
				"example@yourdomain.com" , "error while verifying email");
	}  */
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
