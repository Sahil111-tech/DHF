package com.qa.airteam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class quickQuote extends BaseTest {
	// constructor for page factory
	public quickQuote() {
		// initialize page factory
		PageFactory.initElements(driver, this);
	}

	// Locator for the Get Quote button to initiate the quote process
	@FindBy(xpath = "//a[text()='Get a quote'][1]")
	private WebElement getQuote;

	// Locator to select cover type as Single
	@FindBy(id = "choice-SINGLE")
	private WebElement coverFor;

	// Locator for the Next button to proceed to the next step in the quote
	// process
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement clickNext;

	// Locator to click on "where do you living?"
	@FindBy(xpath = "//button[text()='NSW']")
	private WebElement clickResidence;

	// Locator for the input field to enter the day of birth
	@FindBy(name = "yourDobDay")
	private WebElement inputDate;

	// Locator for the input field to enter the month of birth
	@FindBy(name = "yourDobMonth")
	private WebElement inputMonth;

	// Locator for the input field to enter the year of birth
	@FindBy(name = "yourDobYear")
	private WebElement inputYear;

	// Locator for the button to select the annual income option
	@FindBy(id = "choice-0")
	private WebElement selectAnnualIncome;

	// Locator for the "Calculate cover" button to click on proceed further
	@FindBy(xpath = "//div[text()='Calculate cover']")
	private WebElement clickCalculateCover;

	/***************************
	 * Below are the Actions methods for above locators
	 *****************************************/

	// Action method to click the "Get Quote" button
	public void clickGetQuote() {
		waitVisibility(getQuote); // Wait for the element to be visible
		click(getQuote);
	}

	// Action method to select cover type as "Single"
	public void selectCoverForSingle() {
		waitVisibility(coverFor); // Wait for the element to be visible
		click(coverFor);
	}

	// Action method to click the "Next" button after selection of cover type
	public void clickNext() {
		waitVisibility(clickNext); // Wait for the element to be visible
		click(clickNext);
	}

	// Action method to select residence as "NSW"
	public void clickResidenceNSW() {
		waitVisibility(clickResidence); 
		click(clickResidence);
	}

	// Action method to enter the date of birth
	public void enterDateOfBirth(String day) {
		waitVisibility(inputDate); 
		sendKeys(inputDate, day);
	}

	// Action method to enter the month of birth
	public void enterMonthOfBirth(String month) {
		waitVisibility(inputMonth); 
		sendKeys(inputMonth, month);
	}

	// Action method to enter the year of birth
	public void enterYearOfBirth(String year) {
		waitVisibility(inputYear); 
		sendKeys(inputYear, year);
	}

	// Action method to select the annual income options
	public void selectAnnualIncomeOption() {
		waitVisibility(selectAnnualIncome); 
		click(selectAnnualIncome); //wait 
		
	}

	// Action method to click the "Calculate cover" button
	public void clickCalculateCover() {
		waitVisibility(clickCalculateCover); 
		click(clickCalculateCover);
	}

}
