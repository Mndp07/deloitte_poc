package com.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.Log;

public class Deloitte_Careers_Home_Pages extends Driver{

	@FindBy(xpath = "(//input[@aria-label='Search by keyword'])[2]")
	WebElement search_text_field;
	
	@FindBy(xpath = "(//input[@value='Search Jobs'])[2]")
	WebElement search_jobs_button;
	
	@FindBy(id = "title")
	WebElement search_title;
	
	@FindBy(id = "location")
	WebElement search_location;
	
	@FindBy(id = "searchfilter-submit")
	WebElement filter_button;
	
	@FindBy(xpath = "(//a[contains(text(),'Automation Tester')])[1]")
	WebElement jobwithTitle;
	
	@FindBy(xpath = "//button[text()='Apply now ']")
	WebElement applyNowButton;
	
	@FindBy(id = "applyOption-bottom-manual")
	WebElement applyNowToggleOption;
	
	@FindBy(id = "cookie-accept")
	WebElement acceptCookies;
	
	public Deloitte_Careers_Home_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void search_job(String jobname) {
		Log.info("Method to Search the jobs");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		acceptCookies.click();
		search_text_field.click();
		search_text_field.sendKeys(jobname);
	}
	
	public void searchJobsButton() {
		Log.info("Method to click on the seach jobs button");
		search_jobs_button.click();
	}
	
	public void titleOfJob(String title) {
		Log.info("Method to search for a specific job title");
		search_title.sendKeys(title);
	}
	
	public void locationOfJob(String location) {
		Log.info("Method to search for a specific location");
		search_location.sendKeys(location);
	}
	
	public void filter() {
		Log.info("Method to click on filter button");
		filter_button.click();
	}
	
	public void specificJob() {
		Log.info("Method to click on a specific job from filtered list");
		jobwithTitle.click();
	}
	
	public void applyNow_field() {
		Log.info("Method to click apply now button");
		applyNowButton.click();
	}
	
	public void applyNowToggle_field() {
		Log.info("Method to click apply now button");
		applyNowToggleOption.click();
	}

	public void navigateTo_HomePage() {
		Log.info("Method to navigate to Application URL");
		driver.get(prop.getProperty("Deloitte_Careers_URL"));
	}
	
	public void scrollDown() {
		Log.info("Method to scroll down");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800)", "");
	}
	
}
