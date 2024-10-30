package com.qa.airteam.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;
import com.qa.airteam.pages.*;

public class StarterExtrasEligibilityTest extends BaseTest {
	// constructor for initialization page factory and drivers
	public StarterExtrasEligibilityTest(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Here I need to add javascript method to drag the dropdown at mid */

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
	
	//Locator to click on choose cover
	@FindBy(xpath="//button[text()='Choose cover']")
	protected WebElement clickChooseCover;

	/***************************
	 * Below are the Actions methods for above locators
	 *****************************************/

	// Action method to select "Smart Starter Bronze Plus"
//	public void selectSmartStarterBronzePlus() {
//		waitVisibility(smartStarterBronzePlusOption);
//		click(smartStarterBronzePlusOption);
//	}

	public WebElement getSmartStarterBronzePlusOption() {
	    waitVisibility(smartStarterBronzePlusOption);
	    return smartStarterBronzePlusOption;
	}
//	// Action method to select "Prime Choice"
//	public void selectPrimeChoice() {
//		waitVisibility(primeChoiceOption);
//		click(primeChoiceOption);
//	}
	
	public WebElement getPrimeChoice() {
	waitVisibility(primeChoiceOption);
	return primeChoiceOption;
	}

	// Action method to select "Top Cover"
//	public void selectTopCover() {
//		waitVisibility(topCoverOption);
//		click(topCoverOption);
//	}
	
	public WebElement getTopCover() {
	    waitVisibility(topCoverOption);
	    return topCoverOption;
	}

	// Action method to select "Starter Extras"
	public void selectStarterExtras() {
		waitVisibility(starterExtrasOption);
		click(starterExtrasOption);
	}

	// Method to check if "Starter Extras" is enabled
	public boolean isStarterExtrasEnabled() {
		waitVisibility(starterExtrasOption);
		return starterExtrasOption.isEnabled();
	}

	// Method to check if "Starter Extras" is disabled
	public boolean isStarterExtrasDisabled() {
		waitVisibility(starterExtrasOption);
		return !starterExtrasOption.isEnabled();
	}

	// Action method to select click on cover type and then select "Extras Only"
	// from dropdown.
	

	// Action method to click "Edit button"
	public void clickEditButton() {
		waitVisibility(clickEditBtn);
		click(clickEditBtn);
	}

	// Action method to select a specified coverage type e.g. Hospital & Extras, Extra only, Hospital only from the dropdown
	public void selectCoverType(String coverType) {
	    click(coverTypeDropdown); // Open dropdown
	    Select coverDropdown = new Select(coverTypeDropdown);
	    List<WebElement> options = coverDropdown.getOptions();
	    boolean found = false;

	    for (WebElement option : options) {
	        if (option.getText().equalsIgnoreCase(coverType)) {  // Match cover type dynamically
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
	
	// Action method to select a specified coverage-for e.g. Family,Couple,Single Parent,Family from the dropdown
	public void selectCoverFor(String coverFor) {
	    click(coverageForDropdown); // Open dropdown
	    Select coverForDrop = new Select(coverageForDropdown);
	    List<WebElement> options = coverForDrop.getOptions();
	    boolean found = false;

	    for (WebElement option : options) {
	        if (option.getText().equalsIgnoreCase(coverFor)) {  // Match cover type dynamically
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
		waitVisibility(inputPartnerDate);
		sendKeys(inputPartnerDate, day);
	}

	// Action method to enter the month of birth
	public void enterPartnerMonthOfBirth(String month) {
		waitVisibility(inputPartnerMonth);
		sendKeys(inputPartnerMonth, month);
	}

	// Action method to enter the year of birth
	public void enterPartnerYearOfBirth(String year) {
		waitVisibility(inputPartnerYear);
		sendKeys(inputPartnerYear, year);
	}

	// Action method to click the "Click Update Details" button
	public void clickUpdateDetails() {
		waitVisibility(clickUpdateDetailsBtn); // Wait for the element to be visible
		click(clickUpdateDetailsBtn);
	}
	
	// Action method to click on "Choose cover"
		public void clickChooseCovers() {
			waitVisibility(clickChooseCover);
			click(clickChooseCover);
		}


}
