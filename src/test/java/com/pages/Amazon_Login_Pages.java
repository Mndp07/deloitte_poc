package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.Driver;
import com.utilities.Log;

public class Amazon_Login_Pages extends Driver{
	
	@FindBy(id = "ap_email")
	WebElement email_field;
	
	@FindBy(id = "continue")
	WebElement continue_button;
	
	@FindBy(id = "ap_password")
	WebElement password_field;
	
	@FindBy(id = "signInSubmit")
	WebElement signIn_button;
	
	@FindBy(xpath = "//*[@id='auth-error-message-box']/div/div/ul/li/span")
	WebElement error_msg;
	
	public Amazon_Login_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void email_text(String email) {
		Log.info("Method to send email details");
		email_field.sendKeys(email);
	}
	
	public void continue_button() {
		Log.info("Method to click on continue button");
		continue_button.click();
	}
	
	public void password_field(String pwd) {
		Log.info("Method to send password details");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(password_field));
		password_field.sendKeys(pwd);
	}
	
	public void signin_field() {
		Log.info("Method to click on Sign in button");
		signIn_button.click();
	}
	
	public String getErrorMessage() {
		Log.info("Method to error msg on incorrect creds");
		String msg = error_msg.getText();
		return msg;
	}

}
