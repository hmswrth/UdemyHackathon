package com.udemy.qa.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.udemy.qa.base.Main;

public class SearchResultsPage extends Main {
	
	public List <WebElement> listOfCourses = new ArrayList<WebElement>();
	public boolean result = true;
	public Map<Integer, Object[]> dataset;




	// Page Factory - Object Repository
	@FindBy(xpath = "//h1")
	WebElement searchResultsHeader;

	@FindBy(xpath = "//label[@for='filter-button' and @data-purpose='open-filters']")
	WebElement filterBtn;

	@FindBy(xpath = "//*[@id='filter-form']/div/div[5]/label")
	WebElement levelListBox;

	@FindBy(xpath = "//*[@id='filter-form']/div/div[5]/div/div/div/div/div/fieldset/label[2]")
	WebElement beginnerLevelFilter;

	@FindBy(xpath = "//*[@id='filter-form']/div/div[6]/label")
	WebElement languageListBox;

	@FindBy(xpath = "//*[@id='filter-form']/div/div[6]/div/div/div/div/div/fieldset/label[1]")
	WebElement englishLanguageFilter;

	@FindAll(@FindBy(xpath = "//*[@id='udemy']/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/a[1]/div[1]/div[1]"))
	List<WebElement> courseNames;
	
	@FindAll(@FindBy(xpath = "//*[@id='udemy']/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/a[1]/div[1]/div[2]"))
	List<WebElement> authorNames;
	
	@FindAll(@FindBy(xpath = "//*[@id='udemy']/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/a[1]/div[1]/div[4]/span[2]"))
	List<WebElement> lecturesCount;
	
	@FindAll(@FindBy(xpath = "//*[@id='udemy']/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/a[1]/div[1]/div[4]/span[1]"))
	List<WebElement> duration;
	
	@FindAll(@FindBy(xpath = "//*[@id='udemy']/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/a[1]/div[1]/div[3]/span[1]/span[2]"))
	List<WebElement> rating;
	
	@FindAll(@FindBy(xpath = "//*[@id='udemy']/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/a[1]/div[1]/div[3]/span[3]"))
	List<WebElement> totalRating;
	

	// initializing page objects
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyResultsPageTitle() {
		return driver.getTitle();
	}

	public void clickFilterButton() {
		js.executeScript("arguments[0].scrollIntoView(true)", filterBtn);
		filterBtn.click();
	}

	public void expandLevelListBox() {
		js.executeScript("arguments[0].scrollIntoView(true)", levelListBox);
		levelListBox.click();
	}

	public void applyBeginnerLevelFilter() {
		js.executeScript("arguments[0].scrollIntoView(true)", beginnerLevelFilter);
		beginnerLevelFilter.click();
	}

	public void expandLanguageListBox() {
		js.executeScript("arguments[0].scrollIntoView(true)", languageListBox);
		languageListBox.click();
	}

	public void applyEnglishLanguageFilter() {
		js.executeScript("arguments[0].scrollIntoView(true)", englishLanguageFilter);
		englishLanguageFilter.click();
	}

	public void getSortedResults() {
		listOfCourses.addAll(courseNames);
		listOfCourses.addAll(authorNames);
		listOfCourses.addAll(lecturesCount);
		listOfCourses.addAll(duration);
		listOfCourses.addAll(rating);
		listOfCourses.addAll(totalRating);
		
		try {
			List<WebElement> courseTitles = listOfCourses.subList(0, 12);
			List<WebElement> authors = listOfCourses.subList(12, 24);
			List<WebElement> totalLectures = listOfCourses.subList(24, 36);
			List<WebElement> totalHours = listOfCourses.subList(36, 48);
			List<WebElement> rating = listOfCourses.subList(48, 60);
			List<WebElement> totalRating = listOfCourses.subList(60, 72);

			result = true;

			dataset = new TreeMap< Integer, Object[]>();
			dataset.put(1, new Object[] { "Course Title", "Author", "Total Lectures", "Total Hours", "Rating",
					"Total Ratings" });
			for (int i = 0; i < 12; i++) {
				dataset.put(i+2,
						new Object[] { courseTitles.get(i).getText(), authors.get(i).getText().replaceAll("By ", ""),
								totalLectures.get(i).getText(), totalHours.get(i).getText(), rating.get(i).getText(),
								totalRating.get(i).getText() });
			}
			
			//printing all course details to the console
			
			Collection<Object[]> keys = dataset.values();
			
			for(Object o: keys) {
			    for(int i = 0; i < 6; i++) {
			        System.out.println(((Object[])o)[i].toString());  //object is an array in java so can be accessed using index
			    }
			    System.out.println("...................");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			
		}

	}
	


}
