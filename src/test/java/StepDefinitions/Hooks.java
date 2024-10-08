package StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.qa.BaseTest;
import com.qa.airteam.pages.quickQuote;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	quickQuote quote;
	BaseTest baseTest = new BaseTest();
	WebDriver driver;
	// Initialize logger
    public static final Logger log = LogManager.getLogger(CreateQuoteSteps.class);
    
	@Before
	public void i_open_the_browser() {
		log.info("Opening browser...");
		// Initialize the browser
		baseTest.initialize();
        // Retrieve the driver from BaseTest
		driver = baseTest.getDriver();
		log.info("Browser opened successfully.");
	}
	
	@After
	public void tearDown() {
	baseTest.tearDown(null);
	}

}
