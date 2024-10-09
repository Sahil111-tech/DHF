package StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.qa.BaseTest;
import com.qa.airteam.pages.quickQuote;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	BaseTest baseTest = new BaseTest();
	WebDriver driver;
	// Initialize logger
	public static final Logger log = LogManager.getLogger(Hooks.class);

	@Before
	public void i_open_the_browser() {
		log.info("Opening browser...");
		baseTest.initialize(); // Initialize the browser
		driver = baseTest.getDriver(); // Retrieve the WebDriver
		log.info("Browser opened successfully.");
	}

	@After
	public void tearDown(Scenario scenario) {
		 if (driver != null) {
		        driver.quit(); 
		    } // Close the browser
	}

	public WebDriver getDriver() {
		return driver; // Getter to provide WebDriver instance
	}

	
	/*
	 * @AfterStep
	 * 
	 * public void addScreenshot(Scenario scenario) {
	 * 
	 * if(scenario.isFailed()) {
	 * 
	 * TakesScreenshot ts = (TakesScreenshot) driver; byte[] screenshot =
	 * ts.getScreenshotAs(OutputType.BYTES); scenario.attach(screenshot,
	 * "image/png", scenario.getName());
	 * 
	 * }
	 * 
	 * }
	 */
	 

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		if (driver != null) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			
			if (scenario.isFailed()) {
				
				scenario.attach(screenshot, "image/png", "Screenshot for failed step: " + scenario.getName());
			} else {
				
				scenario.attach(screenshot, "image/png", "Screenshot for passed step: " + scenario.getName());
			}
		}

	
}
}
