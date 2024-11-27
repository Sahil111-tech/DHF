package com.qa.airteam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
	@FindBy(xpath = "//a[@data-id='get-quote-btn' and text()='Get a quote']")
	protected WebElement getQuote;

	// Locator to select cover type as Single
	@FindBy(xpath = "//div[@data-id='status']")
	protected WebElement coverForContainer;

	// Locator for the Next button to proceed to the next step in the quote process
	@FindBy(xpath = "//button[@type='submit']") // "//button[@type='submit']"
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

	// Locator for 'Smart Starter Bronze Plus' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Smart Starter']/../..//button[contains(@data-id, hospital-product)]")
	protected WebElement smartStarterBronzePlusOption;
	// Locator for 'Prime Choice' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Prime Choice']/../..//button[contains(@data-id, 'hospital-product')]")
	protected WebElement primeChoiceOption;

	// Locator for 'Top Cover' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Top Cover']/../..//button[contains(@data-id, 'hospital-product')]")
	protected WebElement topCoverOption;

	// Locator for 'Starter Extras' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Starter Extras']/../..//button[contains(@data-id, hospital-product)]")
	protected WebElement starterExtrasOption;

	// Locator for 'Essential Extras' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Essential Extras']/../..//button[contains(@data-id, hospital-product)]")
	protected WebElement EssentialExtrasOption;

	// Locator for 'Total Extras' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Total Extras']/../..//button[contains(@data-id, hospital-product)]")
	protected WebElement TotalExtrasOption;

	// Locator to click on apply now button
	@FindBy(xpath = "(//div[@class='button-with-icon css-zkfaav' and contains(text(), 'Apply now')])[4]")
	protected WebElement clickApplyNow;

	/***************************
	 * Below are the Actions methods for above locators
	 *****************************************/

	// Action method to click the "Get Quote" button
	public void clickGetQuote() {
		try {
			log.info("Attempting to click the 'Get Quote' button...");
			waitForVisibility(getQuote, driver);
			waitForElementToBeClickable(driver, getQuote);
			click(getQuote);
			log.info("'Get Quote' button clicked successfully.");
		} catch (StaleElementReferenceException e) {
			log.warn(
					"Encountered StaleElementReferenceException for 'Get Quote' button. Reinitializing elements and retrying...");
			PageFactory.initElements(driver, this);
			waitForVisibility(getQuote, driver);
			waitForElementToBeClickable(driver, getQuote);
			click(getQuote);
			log.info("'Get Quote' button clicked successfully after retrying.");
		}
	}

	public void selectCoverFor(String coverType) {
		log.info("Selecting cover type: " + coverType + "...");
		waitVisibility(coverForContainer, driver);
		WebElement coverOption = coverForContainer
				.findElement(By.xpath("//button[contains(text(), '" + coverType + "')]"));
		click(coverOption);
		log.info("Cover type '" + coverType + "' selected successfully.");
	}

	// Action method to click the "Next" button after selection of cover type
	public void clickNext() {
		log.info("Clicking the 'Next' button...");
		waitVisibility(clickNext, driver);
		click(clickNext);
		log.info("'Next' button clicked successfully.");
	}

	// Action method to select residence as residence
	public void selectResidence(String residence) {
		log.info("Selecting residence: " + residence + "...");
		waitVisibility(residenceContainer, driver);
		WebElement residenceOption = residenceContainer
				.findElement(By.xpath("//button[contains(text(), '" + residence + "')]"));
		click(residenceOption);
		log.info("Residence '" + residence + "' selected successfully.");
	}

	public void selectHospitalCover(String coverType) {
		log.info("Selecting hospital cover: " + coverType + "...");
		WebElement selectedOption;

		switch (coverType) {
		case "Smart Starter":
			selectedOption = smartStarterBronzePlusOption;
			break;
		case "Prime Choice":
			selectedOption = primeChoiceOption;
			break;
		case "Top Cover":
			selectedOption = topCoverOption;
			break;
		default:
			log.error("Invalid hospital plan name: " + coverType);
			throw new IllegalArgumentException("Invalid hospital plan name: " + coverType);
		}
	}

	public void selectExtrasCover(String coverType) {

		WebElement selectedOption;

		// Check which plan to select based on the plan name
		switch (coverType) {
		case "Starter Extras":
			selectedOption = starterExtrasOption;
			break;
		case "Essential Extras":
			selectedOption = EssentialExtrasOption;
			break;
		case "Total Extras":
			selectedOption = TotalExtrasOption;
			break;
		default:
			throw new IllegalArgumentException("Invalid extras cover name: " + coverType);
		}

		// Perform the selection
		if (selectedOption != null && selectedOption.isDisplayed()) {
			selectedOption.click();
			log.info("Selected the Extras Cover: " + coverType);
		} else {
			throw new RuntimeException("The Extras cover plan '" + coverType + "' is not available.");
		}

	}

	// Action method to enter the date of birth
	public void enterDateOfBirth(String day) {
		log.info("Entering day of birth: " + day + "...");
		waitVisibility(inputDate, driver);
		sendKeys(inputDate, day);
		log.info("Day of birth entered successfully.");
	}

	// Action method to enter the month of birth
	public void enterMonthOfBirth(String month) {
		log.info("Entering month of birth: " + month + "...");
		waitVisibility(inputMonth, driver);
		sendKeys(inputMonth, month);
		log.info("Month of birth entered successfully.");
	}

	// Action method to enter the year of birth
	public void enterYearOfBirth(String year) {
		log.info("Entering year of birth: " + year + "...");
		waitVisibility(inputYear, driver);
		sendKeys(inputYear, year);
		log.info("Year of birth entered successfully.");
	}

	// Action method to select the annual income options
	public void selectAnnualIncome(String salary) {
		log.info("Selecting annual income: " + salary + "...");
		waitVisibility(annualIncomeContainer, driver);
		WebElement salaryOption = annualIncomeContainer
				.findElement(By.xpath("//button[contains(text(), '" + salary + "')]"));
		click(salaryOption);
		log.info("Annual income '" + salary + "' selected successfully.");
	}

	// Action method to click the "Calculate cover" button
	public void clickCalculateCover() {
		log.info("Clicking 'Calculate Cover' button...");
		waitVisibility(clickCalculateCover, driver);
		click(clickCalculateCover);
		log.info("'Calculate Cover' button clicked successfully.");
	}

	/************************************
	 * Assertions
	 ***************************************************/

	// Method to get the page title
	public String getPageTitle() {
		return driver.getTitle(); // Returns the title of the current page
	}

	// Add this method to check if the Get Quote button is visible
	public boolean isGetQuoteButtonVisible() {
		try {
			return getQuote.isDisplayed(); // Return true if the button is visible
		} catch (Exception e) {
			return false; // Return false if an exception occurs (e.g. element not found)
		}
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl(); // This should return the URL of the current page
	}

	// Action method to select "Click Apply Now Button"
	public void clickApplyNowButton() {
		waitForVisibility(clickApplyNow, driver);
		click(clickApplyNow);
	}

}
