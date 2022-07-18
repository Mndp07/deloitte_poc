package com.pages;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.Log;

public class Amazon_Home_Pages extends Driver{

	@FindBy(xpath = "(//span[text()='All'])[2]")
	WebElement all_hamburger;
	
	@FindBy(xpath = "//div[text()='TV, Appliances, Electronics']")
	WebElement Electronics_link;
	
	@FindBy(xpath = "//a[text()='Televisions']")
	WebElement telivisions_link;
	
	public Amazon_Home_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void all_Hamburger() {
		Log.info("Method to click on All Hamburger Menu");
		all_hamburger.click();
	}
	
	public void electronics_menu() {
		Log.info("Method to click on Tv,Appliances &Electronics Menu");
		Electronics_link.click();
	}
	
	public void televisions_link() {
		Log.info("Method to click on Televisions");
		telivisions_link.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void navigateTo_HomePage() {
		Log.info("Method to navigate to Application URL");
		driver.get(prop.getProperty("Amazon_URL"));
	}
	
	public void scrollDown() {
		Log.info("Method to scroll down");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", "");
	}
	
	public void switchToNewWindow() {
		Log.info("Method to switch to a new window");
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
			}
		}
	}
}
