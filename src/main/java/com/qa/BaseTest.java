package com.qa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;
import com.qa.utils.TestUtils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	protected JavascriptExecutor js;

	public static final Logger log = LogManager.getLogger(BaseTest.class);

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

	public void waitVisibility(WebElement e, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitAndClick(WebElement locator) {
		new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(locator))
				.click();
	}

	// Wait for object to be visible before performing any action
	public void waitForVisibility(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByType(locator)));
	}

	// Wait for object to be visible before performing any action
	public void waitForVisibility(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Overloaded method: Returns boolean to indicate if the element is visible
	public boolean isPopupVisible(WebElement element, WebDriver driver, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true; // Element is visible within the timeout
		} catch (TimeoutException e) {
			log.warn("Element not visible within " + timeoutInSeconds + " seconds.", e);
			return false; // Element not visible within the timeout
		}
	}

	/*
	 * public Boolean verifyObjectIsDisplayed(WebElement element) { try { // Boolean
	 * objectDisplayed=element.isDisplayed(); return element.isDisplayed();
	 * 
	 * } catch (NoSuchElementException | StaleElementReferenceException e) {
	 * 
	 * System.out.println("An unexpected error occurred: " + e.getMessage()); return
	 * false; } }
	 */

	public boolean isElementPresent(By locatorKey, WebDriver driver) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	// Helper method to check if an element is present and displayed
	protected boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed(); // Returns true if the element is visible
		} catch (NoSuchElementException e) {
			return false; // Element not found
		}
	}

	// Wait for an object to be invisible before performing any action
	public void waitForInvisibility(By locator, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Click on a web element
	public void click(WebElement e) {
		waitVisibility(e, driver);
		e.click();
	}/*
		 * /
		 * 
		 * Click on a web element using locator
		 */

	public void click(String locator, WebDriver driver) {
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
	/*
	 * public String getAttribute(WebElement e, String attribute,WebDriver driver) {
	 * waitVisibility(e,driver); return e.getAttribute(attribute); }
	 */

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

	// Overloaded method for just above method (using WebElement)
	public void waitVisibilityElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Helper method to wait for suggestions to appear (if applicable).
	 */
	private void waitForSuggestionsToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
	}

	public void waitForAllOptionsVisibility(WebElement dropdown) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOfAllElements(dropdown.findElements(By.tagName("option"))));
	}

	// Modify waitForVisibility method to handle a list of WebElements
	public void waitForVisibility(List<WebElement> elements, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use an appropriate timeout
		for (WebElement element : elements) {
			wait.until(ExpectedConditions.visibilityOf(element)); // Wait for each element in the list to be visible
		}
	}

	public static void sleepFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Helper Method to Wait for Modal/Pop-Up
	public void waitForModalAndHandle(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			// Wait for the modal or pop-up to be visible
			WebElement modal = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("your-modal-css-selector")));

			// Handle any required action like clicking a button
			modal.findElement(By.cssSelector("button.accept")).click(); // Adjust selector as needed

		} catch (TimeoutException e) {
			throw new RuntimeException("Modal did not appear in time!");
		}
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

	// Generic method to scroll
	public void scrollBy(int xOffset, int yOffset) {
		if (js == null) {
			js = (JavascriptExecutor) driver; // Reinitialize if null
		}
		if (js != null) {
			js.executeScript("window.scrollBy(arguments[0], arguments[1]);", xOffset, yOffset);
		} else {
			throw new IllegalStateException("JavascriptExecutor is still null after reinitialization");
		}
	}

	// Method to scroll to a specific element
	public void scrollToElement(WebElement element, WebDriver driver) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			throw new RuntimeException("Element not visible: " + element, e);
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		log.info("Scrolled to element: " + element);
	}

	public static void scrollToElementOffset(WebElement element, WebDriver driver, int yOffset) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int elementPosition = element.getLocation().getY();
		int offsetPosition = elementPosition - yOffset;
		js.executeScript("window.scrollTo(0, arguments[0]);", offsetPosition);
	}

	public void scrollToElementWithRetry(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 3; i++) {
			try {
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
				new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(element));
				log.info("Scrolled to element: " + element);
				break;
			} catch (Exception e) {
				log.warn("Retrying scroll attempt: " + (i + 1));
			}
		}
	}

	public void waitForPageToLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(webDriver -> {
			try {
				return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
			} catch (NoSuchWindowException e) {
				return false;
			}
		});

	}

	// Method to select a value from dropdown by visible text
	public void selectDropdownByVisibleText(WebElement element, String visibleText, WebDriver driver) {
		// waitVisibility(element, driver);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(visibleText);
		log.info("Selected value from dropdown by visible text: " + visibleText);
	}

	// Method to select a value from dropdown by value attribute
	public void selectDropdownByValue(WebElement element, String value) {
		// waitVisibility(element);
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
		log.info("Selected value from dropdown by value: " + value);
	}

	// Method to select a value from dropdown by index
	public void selectDropdownByIndex(WebElement element, int index) {
		// waitVisibility(element);
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
		log.info("Selected value from dropdown by index: " + index);
	}

	// Example usage of selecting dropdown based on locator
	public void selectDropdownByLocator(String locator, String visibleText) {
		WebElement dropdownElement = getElement(locator);
		selectDropdownByVisibleText(dropdownElement, visibleText, driver);
	}

	public void switchToNewWindow(WebDriver driver) {
		String mainWindowHandle = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	// Method to scoll up to the page
	public static void scrollUpBy400(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -400);");
	}

	// Method to scroll to the Mid of the page
	public static void scrollDownBy500(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);");
	}

	// Generic method to scroll down by a specified number of pixels
	/*
	 * public void scrollByPixels(int pixels,WebDriver driver) { JavascriptExecutor
	 * js = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0," +
	 * pixels + ");"); }
	 */

	public void scrollByPixels(int pixels, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", pixels);
	}

	// Method to scroll to the top of the page
	public void scrollToTop(WebDriver driver) {
		try {
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0);");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Method to retrieve all options from dropdown
	public List<WebElement> getAllDropdownOptions(WebElement element) {
		// waitVisibility(element);
		Select dropdown = new Select(element);
		return dropdown.getOptions();
	}

	public void safelyClickElement(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		int attempts = 0;

		while (attempts < 3) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until clickable
				element.click(); // Attempt to click the element
				log.info("Clicked the element successfully");
				return;
			} catch (ElementClickInterceptedException e) {
				log.warn("Element click intercepted, retrying... attempt: " + (attempts + 1));
				attempts++;

				// Use JavaScript click as a fallback after retries
				if (attempts == 3) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", element); // Scroll into view
					js.executeScript("arguments[0].click();", element); // JavaScript click
					log.info("Clicked the element using JavaScript click as a fallback");
				}
			}
		}
	}

	// Method to generate a random full name with first, middle, and last names
	public String generateRandomFullName() {
		String[] firstNames = { "John", "Jane", "Alex", "Chris", "Taylor", "Sam" };
		String[] middleNames = { "Lee", "Ann", "James", "Ray", "Marie", "Lou" };
		String[] lastNames = { "Smith", "Johnson", "Brown", "Taylor", "Anderson", "Thomas" };

		Random rand = new Random();
		String randomFirstName = firstNames[rand.nextInt(firstNames.length)];
		String randomMiddleName = middleNames[rand.nextInt(middleNames.length)];
		String randomLastName = lastNames[rand.nextInt(lastNames.length)];

		return randomFirstName + " " + randomMiddleName + " " + randomLastName;
	}

	// Method to refresh the page
	protected static void refreshPage(WebDriver driver) {
		driver.navigate().refresh(); // Refresh the page
	}

	private static final String[] FIRST_NAMES = { "Oliver", "Charlotte", "William", "Olivia", "Noah", "Isla", "Jack",
			"Amelia", "Thomas", "Ava" };

	private static final String[] LAST_NAMES = { "Smith", "Jones", "Williams", "Brown", "Taylor", "Wilson", "Johnson",
			"White", "Martin", "Hall" };

	// Method to generate a random full name
	public static String getRandomAustralianName() {
		Random random = new Random();
		String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
		String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
		return firstName + " " + lastName;
	}

	// Helper method to check element visibility
	protected static boolean isElementVisible(WebDriverWait wait, WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}