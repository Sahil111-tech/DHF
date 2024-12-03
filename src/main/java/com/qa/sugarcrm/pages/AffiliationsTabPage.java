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

	// Action method to handle dropdown interactions
	/*
	 * public void selectOptionFromDropdown(String textToEnter) throws
	 * InterruptedException { try { // Step 1: Wait for the dropdown to be clickable
	 * and click it to open waitForElementToBeClickable(driver,
	 * associationDropdown); ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].click();", associationDropdown);
	 * 
	 * // Step 2: Wait for the input box to be visible WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(10));
	 * wait.until(ExpectedConditions.visibilityOf(associationDropdownInputBox));
	 * 
	 * // Step 3: Enter the desired text into the input box
	 * associationDropdownInputBox.sendKeys(textToEnter); Thread.sleep(1000); //
	 * Allow suggestions to appear
	 * 
	 * // Step 4: Iterate through the dropdown options and select the matching one
	 * boolean isOptionSelected = false; for (WebElement option :
	 * associationDropdownOptions) { String optionText = option.getText().trim(); if
	 * (optionText.equalsIgnoreCase(textToEnter)) { option.click();
	 * System.out.println("Selected option: " + optionText); isOptionSelected =
	 * true; break; } }
	 * 
	 * // Step 5: Handle case if no matching option is found if (!isOptionSelected)
	 * { throw new NoSuchElementException("No matching option found for: " +
	 * textToEnter); }
	 * 
	 * } catch (Exception e) {
	 * System.err.println("Error occurred while interacting with the dropdown: " +
	 * e.getMessage()); throw new
	 * RuntimeException("Failed to interact with the dropdown.", e); } }
	 */
	/*
	 * public void selectOptionFromDropdown(String textToEnter) throws
	 * InterruptedException { try { // Wait for the dropdown to be clickable and
	 * open it waitForElementToBeClickable(driver, associationDropdown);
	 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
	 * associationDropdown); Thread.sleep(500); // Allow dropdown to fully open
	 * 
	 * // Wait for the input box to become visible WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(15)); WebElement inputBox =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(
	 * By.xpath("(//input[@class='select2-input'])[4]") ));
	 * 
	 * // Enter text into the input box inputBox.sendKeys(textToEnter);
	 * Thread.sleep(2000); // Allow suggestions to load
	 * 
	 * // Select the matching option from the suggestions WebElement matchingOption
	 * = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//ul[starts-with(@id,'select2-results-')]/li[contains(text(),'" +
	 * textToEnter + "')]" ))); Thread.sleep(1000); matchingOption.click();
	 * 
	 * System.out.println("Selected option: " + textToEnter); } catch
	 * (TimeoutException te) {
	 * System.err.println("Element not found or visible within the wait time: " +
	 * te.getMessage()); throw new
	 * RuntimeException("Dropdown interaction failed due to timeout.", te); } catch
	 * (Exception e) {
	 * System.err.println("Error occurred while interacting with dropdown: " +
	 * e.getMessage()); throw new
	 * RuntimeException("Failed to interact with the dropdown.", e); } }
	 */

	/*
	 * //Selects the dropdown option based on the input value: 1 for the first
	 * option, 2 for the second, and so on public void associationDropdownValues(int
	 * stepsToTraverse) throws InterruptedException { // try { // Wait for the
	 * dropdown to be visible and clickable waitForVisibility(associationDropdown,
	 * driver); waitForElementToBeClickable(driver, associationDropdown);
	 * 
	 * // Scroll the dropdown into view if necessary // ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);",
	 * associationDropdown);
	 * 
	 * // Click to open the dropdown associationDropdown.click();
	 * Thread.sleep(2000); associationDropdown.sendKeys("Not applicable");
	 * //Thread.sleep(2000); System.out.println("Dropdown clicked successfully.");
	 * Thread.sleep(1000); // Use Actions to traverse through options WebElement
	 * activeElement = driver.switchTo().activeElement(); Actions actions = new
	 * Actions(driver); for (int i = 0; i < stepsToTraverse; i++) {
	 * actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
	 * Thread.sleep(1000); // Allow time for dropdown navigation animation }
	 * 
	 * // Press ENTER to select the desired option
	 * actions.sendKeys(Keys.ENTER).perform(); System.out.println("Option " +
	 * stepsToTraverse + " selected successfully.");
	 * 
	 * } catch (ElementClickInterceptedException e) {
	 * System.err.println("Dropdown click intercepted: " + e.getMessage()); throw
	 * new
	 * RuntimeException("Failed to interact with the dropdown due to click interception."
	 * , e); } catch (TimeoutException e) { System.err.
	 * println("Dropdown was not clickable or visible within the wait time: " +
	 * e.getMessage()); throw new
	 * RuntimeException("Failed to locate the dropdown in the given time.", e); }
	 * catch (InterruptedException e) { Thread.currentThread().interrupt(); throw
	 * new RuntimeException("Thread interrupted during sleep.", e); } catch
	 * (Exception e) {
	 * System.err.println("Unexpected error while selecting dropdown value: " +
	 * e.getMessage()); throw new RuntimeException("Unexpected error occurred.", e);
	 * } } } }
	 */
	/*
	 * // Wait for the input box to be visible
	 * waitForVisibility(associationDropdown,driver); associationDropdown.click();
	 * Actions actions = new Actions(driver); for (int i = 0; i < stepsToTraverse;
	 * i++) { actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow
	 * key BaseTest.sleepFor(700); } actions.sendKeys(Keys.ENTER).build().perform();
	 * // hamburgerMenu.click();
	 * 
	 * 
	 * }
	 */
	
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