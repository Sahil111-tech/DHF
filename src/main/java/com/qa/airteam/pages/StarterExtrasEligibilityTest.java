package com.qa.airteam.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.*;

public class StarterExtrasEligibilityTest extends BaseTest {
	// constructor for initialization page factory and drivers
	public StarterExtrasEligibilityTest(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Locator for 'Smart Starter Bronze Plus' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Smart Starter']/../..//button[contains(@data-id, hospital-product)]")
	protected WebElement smartStarterBronzePlusOption;

	// Locator for 'Starter Extras' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Starter Extras']/../..//button[contains(@data-id, hospital-product)]")
	protected WebElement starterExtrasOption;

	// Locator for 'Prime Choice' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Prime Choice']/../..//button[contains(@data-id, 'hospital-product')]")
	protected WebElement primeChoiceOption;

	// Locator for 'Top Cover' checkbox or selection element
	@FindBy(xpath = "//h4[text()='Top Cover']/../..//button[contains(@data-id, 'hospital-product')]")
	protected WebElement topCoverOption;

	// Locator for selecting Extras only from dropdown
	@FindBy(id = "cover") // Correctly specify the ID without XPath
	protected WebElement coverTypeDropdown;

	@FindBy(xpath = "//button[text()='Edit details']")
	protected WebElement clickEditBtn;

	@FindBy(xpath = "//select[@name='status']")
	protected WebElement coverageForDropdown;

	@FindBy(xpath = "//div[text()='Update details']")
	protected WebElement clickUpdateDetailsBtn;

	// Locator for the input field to enter the partner's date of birth
	@FindBy(name = "partnerDay")
	protected WebElement inputPartnerDate;

	// Locator for the input field to enter the month of birth
	@FindBy(name = "partnerMonth")
	protected WebElement inputPartnerMonth;

	// Locator for the input field to enter the year of birth
	@FindBy(name = "partnerYear")
	protected WebElement inputPartnerYear;

	// Locator to click on choose cover
	@FindBy(xpath = "//button[text()='Choose cover']")
	protected WebElement clickChooseCover;

	// Locator to click on apply now button
	@FindBy(xpath = "(//div[@class='button-with-icon css-zkfaav' and contains(text(), 'Apply now')])[3]")
	protected WebElement clickApplyNow;

	@FindBy(xpath = "//div[text()='Health practitioner']")
	protected WebElement clickHealthPractioner;


	// XPath for Submit Your Details Button
	@FindBy(xpath = "//div[@class='css-fbcqx2']/div[2]/button/div/div[text()='Continue']")
	private WebElement yourDetailsContinueButton;

	/***************************
	 * Below are the Actions methods for above locators
	 *****************************************/

	public WebElement getSmartStarterBronzePlusOption() {
		waitVisibility(smartStarterBronzePlusOption, driver);
		return smartStarterBronzePlusOption;
	}

	public WebElement getPrimeChoice() {
		waitVisibility(primeChoiceOption, driver);
		return primeChoiceOption;
	}

	public WebElement getTopCover() {
		waitVisibility(topCoverOption, driver);
		return topCoverOption;
	}

	// Action method to select "Starter Extras"
	public void selectStarterExtras() {
		waitVisibility(starterExtrasOption, driver);
		click(starterExtrasOption);
	}

	// Method to check if "Starter Extras" is enabled
	public boolean isStarterExtrasEnabled() {
		waitVisibility(starterExtrasOption, driver);
		return starterExtrasOption.isEnabled();
	}

	// Method to check if "Starter Extras" is disabled
	public boolean isStarterExtrasDisabled() {
		waitVisibility(starterExtrasOption, driver);
		return !starterExtrasOption.isEnabled();
	}

	// Action method to select click on cover type and then select "Extras Only"
	// from dropdown.

	// Action method to click "Edit button"
	public void clickEditButton() {
		waitVisibility(clickEditBtn, driver);
		click(clickEditBtn);
	}

	// Action method to select a specified coverage type e.g. Hospital & Extras,
	// Extra only, Hospital only from the dropdown
	public void selectCoverType(String coverType) {
		click(coverTypeDropdown); // Open dropdown
		Select coverDropdown = new Select(coverTypeDropdown);
		List<WebElement> options = coverDropdown.getOptions();
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(coverType)) { // Match cover type dynamically
				coverDropdown.selectByVisibleText(coverType);
				log.info("Selected '" + coverType + "' from dropdown.");
				found = true;
				break;
			}
		}

		if (!found) {
			log.warn("Cover type '" + coverType + "' not found in the dropdown.");
		}
	}

	// Action method to select a specified coverage-for e.g. Family,Couple,Single
	// Parent,Family from the dropdown
	public void selectCoverFor(String coverFor) {
		click(coverageForDropdown); // Open dropdown
		Select coverForDrop = new Select(coverageForDropdown);
		List<WebElement> options = coverForDrop.getOptions();
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(coverFor)) { // Match cover type dynamically
				coverForDrop.selectByVisibleText(coverFor);
				log.info("Selected '" + coverFor + "' from dropdown.");
				found = true;
				break;
			}
		}

		if (!found) {
			log.warn("Cover type '" + coverFor + "' not found in the dropdown.");
		}
	}

	// Action method to enter the date of birth
	public void enterPartnerDateOfBirth(String day) {
		waitVisibility(inputPartnerDate, driver);
		sendKeys(inputPartnerDate, day);
	}

	// Action method to enter the month of birth
	public void enterPartnerMonthOfBirth(String month) {
		waitVisibility(inputPartnerMonth, driver);
		sendKeys(inputPartnerMonth, month);
	}

	// Action method to enter the year of birth
	public void enterPartnerYearOfBirth(String year) {
		waitVisibility(inputPartnerYear, driver);
		sendKeys(inputPartnerYear, year);
	}

	// Action method to click the "Click Update Details" button
	public void clickUpdateDetails() {
		waitVisibility(clickUpdateDetailsBtn, driver); // Wait for the element to be visible
		click(clickUpdateDetailsBtn);
	}

	// Action method to click on "Choose cover"
	public void clickChooseCovers() {
		waitVisibility(clickChooseCover, driver);
		click(clickChooseCover);
	}

	// Action method to select "Click Apply Now Button"
	public void clickApplyNowButton() {
		waitVisibility(clickApplyNow, driver);
		click(clickApplyNow);
	}

	public void clickHealthPractionerBtn() {
		click(clickHealthPractioner);

	}


	// Method to click the Your Details Continue Button
	public void clickYourDetailsContinueButton() {
		yourDetailsContinueButton.click();
	}

}
