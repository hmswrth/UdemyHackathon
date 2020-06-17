package com.udemy.qa.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;

import com.udemy.qa.base.Main;
import com.udemy.qa.pages.HomePage;
import com.udemy.qa.pages.SearchResultsPage;

public class SearchResultsPageTest extends Main {
	static HomePage homePage;
	static SearchResultsPage searchResultsPage;
	
	public SearchResultsPageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		init();
		homePage = new HomePage();
		searchResultsPage = homePage.search("web development");
	}
	
	@Test(priority=1)
	public void verifySearchResultsPageTitleTest() {
		String title = searchResultsPage.verifyResultsPageTitle();
		Assert.assertEquals(title, "Web Development Online Courses: Build and Enhance Websites | Udemy" , "search results title page failed");
	}
	
	@Test(priority=2)
	public void expandLevelFilterListBoxTest() {
		searchResultsPage.expandLevelListBox();
	}
	
	@Test(priority=3)
	public void applyBeginnerLevelFilterTest() {
		searchResultsPage.applyBeginnerLevelFilter();
	}
	
	@Test(priority=4)
	public void expandLanguageFilterListBoxTest() {
		searchResultsPage.expandLanguageListBox();
	}
	
	@Test(priority=5)
	public void applyEnglishLanguageFilterTest() {
		searchResultsPage.applyEnglishLanguageFilter();
	}
	
	@Test(priority=6)
	public void closeFilterButtonTest() {
		searchResultsPage.clickFilterButton();
	}
	
	@Test(priority=7)
	public void getFilteredListOfCoursesTest() {
		searchResultsPage.getSortedResults();
		Assert.assertTrue(searchResultsPage.result);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
