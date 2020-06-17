package com.udemy.qa.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;

import com.udemy.qa.base.Main;
import com.udemy.qa.pages.HomePage;
import com.udemy.qa.pages.SearchResultsPage;
import com.udemy.qa.utils.TestUtil;

public class HomePageTest extends Main{
	static HomePage homePage;
	static SearchResultsPage searchResultsPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		init();
		homePage = new HomePage();
	}
	
	@DataProvider
	public Object[][] getSearchDataFromExcel() {
		return TestUtil.getTestData("Search");
	}
	
	@Test(priority = 1, dataProvider = "getSearchDataFromExcel")
	public void searchBoxTest(String search) {
		searchResultsPage = homePage.search(search);	
	}
	
	@Test(priority = 2)
	public void verifySearchResultsPageTitleTest() {
		String title = searchResultsPage.verifyResultsPageTitle();
		Assert.assertEquals(title, "Web Development Online Courses: Build and Enhance Websites | Udemy" , "search results title page failed");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}
