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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.airteam.pages.StarterExtrasEligibilityTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	public static WebDriver driver;
	public static Properties prop;

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
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-web-security", "--user-data-dir=/path/to/temp/dir");

			options.addArguments("--incognito");

			options.addArguments("--disable-web-security");
			options.addArguments("--disable-site-isolation-trials");
			options.addArguments("--disable-dev-shm-usage"); // Reduce resource usage
			options.addArguments("--no-sandbox"); // For certain CI environments

			driver = new ChromeDriver();

			log.info("ChromeDriver initialized.");
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Use WebDriverManager to setup EdgeDriver
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--no-first-run"); // Skip first-run experience
			options.addArguments("--disable-default-apps"); // Disable default apps
			options.addArguments("--disable-extensions"); // Disable extensions
			options.addArguments("-inprivate");
			options.addArguments("--disable-notifications");
	        options.addArguments("--disable-popup-blocking");
			driver = new EdgeDriver(options);
			log.info("EdgeDriver initialized.");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Use WebDriverManager to setup FirefoxDriver
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			// options.setHeadless(false); // Set to true if you want to run headless
			options.addArguments("--disable-dev-shm-usage"); // Reduce resource usage
			options.addArguments("--no-sandbox"); // For certain CI environments
			options.addArguments("--disable-notifications");
	        options.addArguments("--disable-popup-blocking");
			driver = new FirefoxDriver(options);
			log.info("FirefoxDriver initialized.");
		} else {
			log.error("Browser type not supported: " + browserName);
			throw new RuntimeException("Browser type not supported: " + browserName);
		}

		// Maximize window and launch the URL from config.properties
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(url);
		log.info("Navigated to URL: " +url);
		// Clear cookies to avoid stale sessions
		driver.manage().deleteAllCookies();
		log.info("All cookies cleared before starting the test.");
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

	@AfterStep

	public void addScreenshot(Scenario scenario) {

		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}

	}
}

// it is designed to capture and attach screenshots for every step of a scenario
// during test execution
/*
 * @AfterStep public void addScreenshot(Scenario scenario) { if (driver != null)
 * { TakesScreenshot ts = (TakesScreenshot) driver; byte[] screenshot =
 * ts.getScreenshotAs(OutputType.BYTES);
 * 
 * if (scenario.isFailed()) {
 * 
 * scenario.attach(screenshot, "image/png", "Screenshot for failed step: " +
 * scenario.getName()); } else {
 * 
 * scenario.attach(screenshot, "image/png", "Screenshot for passed step: " +
 * scenario.getName()); } }
 * 
 * }
 */
