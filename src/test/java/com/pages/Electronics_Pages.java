package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.driver.Driver;
import com.utilities.Log;

public class Electronics_Pages extends Driver{

	@FindBy(xpath = "//span[text()='Samsung']/../div")
	WebElement brand_samsung;
	
	@FindBy(id = "s-result-sort-select")
	WebElement priority_selection;
	
	@FindBy(xpath = "//a[text()='Price: High to Low']")
	WebElement hightolow_priority_selection;
	
	@FindBy(xpath = "//h1[text()=' About this item ']")
	WebElement aboutThisItem;
	
	@FindBy(id = "buy-now-button")
	WebElement buynow_button;
	
	public Electronics_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void filter_samsung() {
		Log.info("Method to Filter Samsung from a brand menu");
		brand_samsung.click();
	}
	
	public void sortBy_selection() {
		Log.info("Method to select the sorting the items");
		Select sel = new Select(priority_selection);
		sel.selectByValue("price-desc-rank");
	}
	
	public void retrieveSecondHighest(int value) {
		Log.info("Method to retrieve second highest from the existing list of sorted items");
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div["+(value+1)+"]")).click();
	}
	
	public String aboutThisItem_text() {
		Log.info("Method to retrieve the text from the UI");
		return aboutThisItem.getText();
	}
	
	public void buynow() {
		Log.info("Method to click on buy now widget");
		buynow_button.click();
	}
}
