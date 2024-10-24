package com.qa.airteam.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.BaseTest;

public class StarterExtrasEligibilityTest extends BaseTest {
	// constructor for initialization page factory and drivers
	public StarterExtrasEligibilityTest(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	// Locator for the Get Quote button to initiate the quote process
	@FindBy(xpath = "//a[@data-id='get-quote-btn']")
	private WebElement getQuote;

	// Locator to select cover type as Single
	@FindBy(id = "choice-SINGLE")
	private WebElement coverFor;

	// Locator for the Next button to proceed to the next step in the quote process
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

	/* Here I need to add javascript method to drag the dropdown at mid */

	// Locator for 'Smart Starter Bronze Plus' checkbox or selection element
    @FindBy(xpath = "//h4[text()='Smart Starter']/../..//button[contains(@data-id, hospital-product)]")
    WebElement smartStarterBronzePlusOption;

    // Locator for 'Starter Extras' checkbox or selection element
    @FindBy(xpath = "//h4[text()='Starter Extras']/../..//button[contains(@data-id, hospital-product)]")
    WebElement starterExtrasOption;

    // Locator for 'Prime Choice' checkbox or selection element
    @FindBy(xpath = "//h4[text()='Prime Choice']/../..//button[contains(@data-id, 'hospital-product')]")
    WebElement primeChoiceOption;

    // Locator for 'Top Cover' checkbox or selection element
    @FindBy(xpath = "//h4[text()='Top Cover']/../..//button[contains(@data-id, 'hospital-product')]")
    WebElement topCoverOption;
    
    //Locator for selecting Extras only from dropdown
    @FindBy(id = "cover")  // Correctly specify the ID without XPath
    private WebElement coverDropdown;

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
		click(selectAnnualIncome); // wait

	}

	// Action method to click the "Calculate cover" button
	public void clickCalculateCover() {
		waitVisibility(clickCalculateCover);
		click(clickCalculateCover);
	}
	
	// Action method to select "Smart Starter Bronze Plus"
    public void selectSmartStarterBronzePlus() {
        waitVisibility(smartStarterBronzePlusOption);
        click(smartStarterBronzePlusOption);
    }

    // Action method to select "Prime Choice"
    public void selectPrimeChoice() {
        waitVisibility(primeChoiceOption);
        click(primeChoiceOption);
    }

    // Action method to select "Top Cover"
    public void selectTopCover() {
        waitVisibility(topCoverOption);
        click(topCoverOption);
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
    public void selectExtrasIfAvailable() {
        click(coverDropdown);  // Click to open dropdown
        Select coverTypeDropdown = new Select(coverDropdown);
        List<WebElement> options = coverTypeDropdown.getOptions();
        boolean foundExtras = false;
        
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase("Extras only")) {
                coverTypeDropdown.selectByVisibleText("Extras only");
                log.info("Selected 'Extras only' from dropdown.");
                foundExtras = true;  // Set to true when found
                break;  // Exit the loop once the option is found and selected
            }
        }
        
        if (!foundExtras) {
            System.out.println("Extras only not found in the dropdown.");
        }
    }
}
