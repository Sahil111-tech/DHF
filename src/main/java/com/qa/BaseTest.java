package com.qa;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;
import com.qa.utils.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;

	// Constructor to load the config.properties file
	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Initialize the browser based on the config.properties
	@BeforeTest
	public void initialize() {
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browserName.equalsIgnoreCase("chrome")) {
			// Use WebDriverManager to setup ChromeDriver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Use WebDriverManager to setup EdgeDriver
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser type not supported: " + browserName);
		}

		// Maximize window and launch the URL from config.properties
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);
	}

	public WebDriver getDriver() {
		return driver;

	}

	public String getDateTime() {
		// Create a date format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
		// Get the current date and time
		Date date = new Date();
		return formatter.format(date);
	}

	// Method to capture screenshot
	public File captureScreenshot() {

		if (driver instanceof TakesScreenshot) {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		}
		return null;
	}

	public void waitVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	// Wait for object to be visible before performing any action
	public void waitForVisibility(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByType(locator)));
	}

	// Click on a web element
	public void click(WebElement e) {
		waitVisibility(e);
		e.click();
	}/*
		 * /
		 * 
		 * Click on a web element using locator
		 */

	public void click(String locator) {
		waitForVisibility(locator);
		WebElement element = driver.findElement(getByType(locator));
		element.click();
	}

	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	// Send keys to a web element
	public void sendKey(String locator, String txt) {
		waitForVisibility(locator);
		WebElement element = driver.findElement(getByType(locator));
		element.sendKeys(txt);
	}

	// Get attribute from a web element
	public String getAttribute(WebElement e, String attribute) {
		waitVisibility(e);
		return e.getAttribute(attribute);
	}

	// Check if element is displayed
	public Boolean isDisplayed(WebElement element) {
		return element != null && element.isDisplayed();
	}

	// Check if element is displayed using locator
	public Boolean isDisplayed(String locator) {
		WebElement element = driver.findElement(getByType(locator));
		return isDisplayed(element);
	}

	// Builds the WebElement by given locator strategy
	// to retrieve a web element from the webpage
	public WebElement getElement(String locator) {
		WebElement element = null;
		By byType = getByType(locator);
		try {
			element = driver.findElement(byType);
		} catch (Exception e) {
			System.out.println("Element not found with: " + locator);
		}
		return element;
	}

	// Wait for an element to be visible using locator
	public void waitVisibilityElement(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByType(locator)));
	}

	// Type into an element using locator
	public void type(String locator, String value) {
		waitVisibilityElement(locator);
		WebElement element = driver.findElement(getByType(locator));
		element.sendKeys(value);
	}

	// Builds the By type with the given locator strategy
	public By getByType(String locator) {
		By by = null;
		String locatorType = locator.split("=>")[0];
		locator = locator.split("=>")[1];
		try {
			switch (locatorType) {
			case "id":
				by = By.id(locator);
				break;
			case "name":
				by = By.name(locator);
				break;
			case "xpath":
				by = By.xpath(locator);
				break;
			case "css":
				by = By.cssSelector(locator);
				break;
			case "class":
				by = By.className(locator);
				break;
			case "tag":
				by = By.tagName(locator);
				break;
			case "link":
				by = By.linkText(locator);
				break;
			case "partiallink":
				by = By.partialLinkText(locator);
				break;
			default:
				System.out.println("Locator type not supported");
			}
		} catch (Exception e) {
			System.out.println("By type not found with: " + locatorType);
		}
		return by;
	}

	public void waitForPageToLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	// Method to close the browser
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}