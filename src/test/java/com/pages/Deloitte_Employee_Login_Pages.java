package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.Log;

public class Deloitte_Employee_Login_Pages extends Driver{
	
	@FindBy(id = "username")
	WebElement email_field;
	
	@FindBy(id = "password")
	WebElement password_field;
	
	@FindBy(xpath = "//*[@id=\"password\"]/../../../../tr[3]/td[2]/span/span/button")
	WebElement signIn_button;
	
	@FindBy(id = "errorMsg_1")
	WebElement error_msg;
	
	public Deloitte_Employee_Login_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void email_text(String email) {
		Log.info("Method to send email details");
		email_field.sendKeys(email);
	}
	
	public void password_field(String pwd) {
		Log.info("Method to send password details");
		password_field.sendKeys(pwd);
	}
	
	public void signin_field() {
		Log.info("Method to Sign In button");
		signIn_button.click();
	}
	
	public String getErrorMessage() {
		Log.info("Method to error msg on incorrect creds");
		String msg = error_msg.getText();
		return msg;
	}

}
