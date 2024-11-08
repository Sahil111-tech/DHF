package com.qa.airteam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class quickQuote extends BaseTest {

	protected WebDriver driver;

	// constructor for initialization page factory and drivers
	public quickQuote(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for the Get Quote button to initiate the quote process
	@FindBy(xpath = "//a[@data-id='get-quote-btn']")
	protected WebElement getQuote;

	// Locator to select cover type as Single
	@FindBy(xpath = "//div[@data-id='status']")
	protected WebElement coverForContainer;

	// Locator for the Next button to proceed to the next step in the quote process
	@FindBy(xpath = "//button[@type='submit']") //"//button[@type='submit']"
	protected WebElement clickNext;

	// Locator to click on "where do you living?"
	@FindBy(xpath = "//div[@data-id='region']")
	protected WebElement residenceContainer;

	// Locator for the input field to enter the day of birth
	@FindBy(name = "yourDobDay")
	protected WebElement inputDate;

	// Locator for the input field to enter the month of birth
	@FindBy(name = "yourDobMonth")
	protected WebElement inputMonth;

	// Locator for the input field to enter the year of birth
	@FindBy(name = "yourDobYear")
	protected WebElement inputYear;

	// Locator for the button to select the annual income option
	@FindBy(xpath = "//div[@data-id='tier']")
	protected WebElement annualIncomeContainer;

	// Locator for the "Calculate cover" button to click on proceed further
	@FindBy(xpath = "//div[text()='Calculate cover']")
	protected WebElement clickCalculateCover;

	/***************************
	 * Below are the Actions methods for above locators
	 *****************************************/

	// Action method to click the "Get Quote" button
	public void clickGetQuote() {
		//waitVisibility(getQuote); // Wait for the element to be visible
		waitForVisibility(getQuote, driver);
		waitForElementToBeClickable(driver, getQuote);
		click(getQuote);
	}

	public void selectCoverFor(String coverType) {
		// Wait for the cover type container to be visible
		waitVisibility(coverForContainer, driver);

		// Construct the specific radio button WebElement based on the cover type name
		WebElement coverOption = coverForContainer
				.findElement(By.xpath("//button[contains(text(), '" + coverType + "')]"));

		// Click on the specific cover option
		click(coverOption);

		log.info("Selected cover for: " + coverType);
	}

	// Action method to click the "Next" button after selection of cover type
	public void clickNext() {
		waitVisibility(clickNext, driver); // Wait for the element to be visible
		click(clickNext);
	}

	// Action method to select residence as residence
	public void selectResidence(String residence) {
		// Wait for the residence container to be visible
		waitVisibility(residenceContainer, driver);

		// Constructed the specific radio button WebElement based on the residence name
		WebElement residenceOption = residenceContainer
				.findElement(By.xpath("//button[contains(text(), '" + residence + "')]"));

		// Click on the specific residence option
		click(residenceOption);

		log.info("Selected residence: " + residence);

	}

	// Action method to enter the date of birth
	public void enterDateOfBirth(String day) {
		waitVisibility(inputDate, driver);
		sendKeys(inputDate, day);
	}

	// Action method to enter the month of birth
	public  void enterMonthOfBirth(String month) {
		waitVisibility(inputMonth, driver);
		sendKeys(inputMonth, month);
	}

	// Action method to enter the year of birth
	public  void enterYearOfBirth(String year) {
		waitVisibility(inputYear, driver);
		sendKeys(inputYear, year);
	}

	// Action method to select the annual income options
	public  void selectAnnualIncome(String salary) {
		// Wait for the annual income container to be visible
		waitVisibility(annualIncomeContainer, driver);

		// Constructed the specific radio button WebElement based on the salary name
		WebElement salaryOption = annualIncomeContainer
				.findElement(By.xpath("//button[contains(text(), '" + salary + "')]"));

		// Click on the specific salary option
		click(salaryOption);

		log.info("Selected annual income: " + salary);
	}

	// Action method to click the "Calculate cover" button
	public  void clickCalculateCover() {
		waitVisibility(clickCalculateCover, driver);
		click(clickCalculateCover);
	}

	/************************************
	 * Assertions
	 ***************************************************/

	// Method to get the page title
	public  String getPageTitle() {
		return driver.getTitle(); // Returns the title of the current page
	}

	// Add this method to check if the Get Quote button is visible
	public  boolean isGetQuoteButtonVisible() {
		try {
			return getQuote.isDisplayed(); // Return true if the button is visible
		} catch (Exception e) {
			return false; // Return false if an exception occurs (e.g. element not found)
		}
	}

	public  String getCurrentURL() {
		return driver.getCurrentUrl(); // This should return the URL of the current page
	}

}
