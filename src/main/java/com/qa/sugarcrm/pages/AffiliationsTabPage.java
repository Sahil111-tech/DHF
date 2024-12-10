package com.qa.sugarcrm.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;

public class AffiliationsTabPage extends BaseTest {
	protected WebDriver driver;

	// constructor for initialization page factory and drivers
	public AffiliationsTabPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for affiliation button
	@FindBy(xpath = "//span[text()='Affiliations']")
	public static WebElement affiliationsTab;

	// Locator for Association
	@FindBy(xpath = "//span[text()='Association']")
	public WebElement association;

	@FindBy(xpath = "//span[@data-fieldname='association_c']")
	WebElement associationDropdown;

	// WebElement for the input box inside the dropdown
	@FindBy(xpath = "(//div[@class='select2-search'])[4]//input[@class='select2-input']")
	WebElement associationDropdownInputBox;

	// Locate the dropdown options list
	@FindBy(xpath = "(//div[@class='select2-search'])[4]//following::ul[starts-with(@id,'select2-results-')]/li")
	List<WebElement> associationDropdownOptions;

	/***************************
	 * Actions Method
	 *********************************************************/
	// Method to click on the affiliation button
	public void clickAffiliationsTab() {
		waitForElementToBeClickable(driver, affiliationsTab);
		affiliationsTab.click();
	}

	// Action method to click on Association
	public void clickAssociation() {
		waitForVisibility(association, driver); // Wait for the element to be visible
		waitForElementToBeClickable(driver, association);
		association.click(); // Perform the click action
	}

		
	public void selectOptionFromAssociationDropdown(int stepsToTraverse) throws InterruptedException {
		// Refresh the element to avoid stale element reference
	    //PageFactory.initElements(driver, this);

	    // Wait for the dropdown to be visible
	    waitForVisibility(associationDropdown,driver);
      // Wait for the dropdown to be clickable and open it
	        waitForElementToBeClickable(driver, associationDropdown);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", associationDropdown);
	        Thread.sleep(500); // Allow dropdown to fully open

	    // Create Actions instance
	    Actions actions = new Actions(driver);

	    // Traverse the dropdown options using the down arrow key
	    for (int i = 0; i < stepsToTraverse; i++) {
	        actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
	        BaseTest.sleepFor(700); // Add delay for dropdown to respond
	    }

	    // Press Enter to select the option
	    actions.sendKeys(Keys.ENTER).build().perform();
	}

}