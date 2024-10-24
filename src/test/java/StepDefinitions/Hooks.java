package StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.BaseTest;
import com.qa.airteam.pages.StarterExtrasEligibilityTest;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks  {
	public static WebDriver driver;
	public static Properties prop;
	public static final Logger log1 = LogManager.getLogger(Hooks.class);

	// Initialize logger
	public static final Logger log = LogManager.getLogger(Hooks.class);

	@Before(order = 0)
	public static void i_open_the_browser() {

		// to load the config value
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			prop.load(fis);
		} catch (IOException e) {
			log.error("Unable to load properties file: " + e.getMessage());
			throw new RuntimeException("Unable to load properties file: " + e.getMessage());
		}
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		log.info("Browser selected: " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			// Use WebDriverManager to setup ChromeDriver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("ChromeDriver initialized.");
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Use WebDriverManager to setup EdgeDriver
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.info("EdgeDriver initialized.");
		} else {
			log.error("Browser type not supported: " + browserName);
			throw new RuntimeException("Browser type not supported: " + browserName);
		}

		// Maximize window and launch the URL from config.properties
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(url);
		log.info("Navigated to URL: " + url);
	}

	// @After
	public void tearDown(Scenario scenario) {
		if (driver != null) {
			driver.quit();
		} // Close the browser
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static StarterExtrasEligibilityTest getStarterExtras() {
		return getStarterExtras(); // Getter to provide the page object instance
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
