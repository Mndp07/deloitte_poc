package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.Electronics_Pages;
import com.pages.Amazon_Home_Pages;
import com.pages.Amazon_Login_Pages;
import com.pages.Deloitte_Careers_Home_Pages;
import com.pages.Deloitte_Employee_Login_Pages;
import com.utilities.Log;
import com.utilities.ReadPropertiesFile;

public class Deloitte_Careers_Test extends Driver{

	public static final String filename = null;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);
	public Deloitte_Careers_Home_Pages  home_pages;
	public Deloitte_Employee_Login_Pages  login_Pages;
	
	@BeforeClass(alwaysRun=true)
	public void pageInstantiation() throws Exception {
		login_Pages = new Deloitte_Employee_Login_Pages(driver);
		home_pages = new Deloitte_Careers_Home_Pages(driver);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void init() {
		Log.info("Inside before method ");
		home_pages.navigateTo_HomePage();
	}
	
	@Test(priority = 1)
	public void aboutThisItem_test() throws Exception {
		Log.info("Test Started ");
		home_pages.scrollDown();
		home_pages.search_job("Selenium");
		home_pages.searchJobsButton();
		home_pages.scrollDown();
		home_pages.titleOfJob("Automation");
		home_pages.locationOfJob("Toronto, Ontario");
		home_pages.filter();
		home_pages.specificJob();
		home_pages.applyNow_field();
		home_pages.applyNowToggle_field();
		login_Pages.email_text("test@test.ca");
		login_Pages.password_field("Test@123%");
		login_Pages.signin_field();
		String actual = login_Pages.getErrorMessage();
		Assert.assertEquals(actual, "Invalid username or password. Please re-enter your login info.");
		Log.info("Test Ended ");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		Log.info("Inside After Method");
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@AfterClass
	public void quit() {
		Log.info("Inside After Class");
		driver.quit();
	}
}