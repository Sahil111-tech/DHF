package com.qa;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Scrolled to element: " + element);
    }

	public void waitForPageToLoad(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait.until(webDriver -> {
	        try {
	            return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
	        } catch (NoSuchWindowException e) {
	            return false;
	        }
	    });
	    
	   }
	

    // Method to select a value from dropdown by visible text
    public void selectDropdownByVisibleText(WebElement element, String visibleText) {
        waitVisibility(element);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
        log.info("Selected value from dropdown by visible text: " + visibleText);
    }

    // Method to select a value from dropdown by value attribute
    public void selectDropdownByValue(WebElement element, String value) {
        waitVisibility(element);
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
        log.info("Selected value from dropdown by value: " + value);
    }

    // Method to select a value from dropdown by index
    public void selectDropdownByIndex(WebElement element, int index) {
        waitVisibility(element);
        Select dropdown = new Select(element);
        dropdown.selectByIndex(index);
        log.info("Selected value from dropdown by index: " + index);
    }

    // Example usage of selecting dropdown based on locator
    public void selectDropdownByLocator(String locator, String visibleText) {
        WebElement dropdownElement = getElement(locator);
        selectDropdownByVisibleText(dropdownElement, visibleText);
    }
    public static void scrollUpBy400(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -400);");
    }
    public static void scrollDownBy500(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
    }
    // Method to retrieve all options from dropdown
    public List<WebElement> getAllDropdownOptions(WebElement element) {
        waitVisibility(element);
        Select dropdown = new Select(element);
        return dropdown.getOptions();
    }
    
    

}