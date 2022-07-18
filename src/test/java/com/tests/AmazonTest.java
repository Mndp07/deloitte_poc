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
import com.utilities.Log;
import com.utilities.ReadPropertiesFile;

public class AmazonTest extends Driver{

	public static final String filename = null;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);
	public Amazon_Home_Pages  home_pages;
	public Electronics_Pages  electronics_Pages;
	public Amazon_Login_Pages  login_Pages;
	
	@BeforeTest
	public void logging() throws IOException {
		Log.info("Inside before class ");
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_HH_mm_ss");
		Date date= new Date();
		String reportDir = dateFormat.format(date);
		System.setProperty("log_dir",reportDir + "//logs//testlog.log");
		File file = new File(System.getProperty("user.dir") + "//properties//log4j.properties");
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(file)) {
		properties.load(inputStream);
		}
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		Configuration config = new PropertiesConfigurationBuilder()
		.setConfigurationSource(ConfigurationSource.NULL_SOURCE).setRootProperties(properties)
		.setLoggerContext(context).build();
		context.setConfiguration(config);
		Configurator.initialize(config);
	}
	
	@BeforeClass(alwaysRun=true)
	public void pageInstantiation() throws Exception {
		login_Pages = new Amazon_Login_Pages(driver);
		home_pages = new Amazon_Home_Pages(driver);
		electronics_Pages = new Electronics_Pages(driver);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void init() {
		Log.info("Inside before method ");
		Driver.init(prop.getProperty("Browser"));
		home_pages.navigateTo_HomePage();
	}
	
	@Test(priority = 1)
	public void aboutThisItem_test() throws Exception {
		Log.info("Test Started ");
		login_Pages = new Amazon_Login_Pages(driver);
		home_pages = new Amazon_Home_Pages(driver);
		electronics_Pages = new Electronics_Pages(driver);
		home_pages.all_Hamburger();
		home_pages.scrollDown();
		home_pages.electronics_menu();
		home_pages.televisions_link();
		home_pages.scrollDown();
		electronics_Pages.filter_samsung();
		electronics_Pages.sortBy_selection();
		electronics_Pages.retrieveSecondHighest(2);
		home_pages.switchToNewWindow();
		home_pages.scrollDown();
		electronics_Pages.buynow();
		login_Pages.email_text("test.test@ca");
		login_Pages.continue_button();
		String actual = login_Pages.getErrorMessage();
		Assert.assertEquals(actual, "We cannot find an account with that email address");
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
	
}