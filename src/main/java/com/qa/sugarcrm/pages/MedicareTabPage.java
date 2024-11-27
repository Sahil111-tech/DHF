package com.qa.sugarcrm.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class MedicareTabPage extends BaseTest {
	protected WebDriver driver;
	// Public static variable to store the provided card number
    public static String providedCardNumber;
 // Public static variable to store the year
    public static int providedYear;

	// constructor for initialization page factory and drivers
	public MedicareTabPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for Medicare button
	@FindBy(xpath = "//span[text()='Medicare']")
	public static WebElement medicareButton;

	// Locator for Edit button
	@FindBy(xpath = "//a[text()='Edit']")
	public static WebElement editButton;

	// Locator for Medicare card type dropdown button
	@FindBy(xpath = "//span[@data-fieldname='medicare_card_type_c']")
	public static WebElement medicareCardTypeButton;
	
	// Xpath to locate the Medicare Card Number input field
	@FindBy(xpath = "//input[@name='medicarecardnumber_c']")
    WebElement medicareCardNumberField;
	
	@FindBy(xpath="//span[@data-fieldname='medicarecardexpirymonth_c']")
	WebElement medicareExpiryMonthDropdown;
	
	@FindBy(xpath="//span[@data-fieldname='is_rebate_applicant_c']")
	WebElement isApplicantClaimingRebateDropdown;
	
	@FindBy(xpath="//span[@data-fieldname='all_people_hold_policy']")
	WebElement IsAllPeopleholdPolicy;
	
	 // Locator for the Medicare Card Expiry Year field
    @FindBy(xpath = "//input[@name='medicarecardexpiryyear_c']")
    public WebElement medicareCardExpiryYearField;

	/***************************
	 * Actions Method
	 *********************************************************/

	// Method to click on the Medicare button
	public void clickMedicareButton() {
		waitForElementToBeClickable(driver, medicareButton);
		medicareButton.click();
	}

	/*
	 * // Method to click on the Edit button public void clickEditButton() {
	 * waitForVisibility(editButton, driver); waitForElementToBeClickable(driver,
	 * editButton); editButton.click(); }
	 */
	
	// Method to click on the Edit button with retry logic
	public void clickEditButton() {
	    int attempts = 0;
	    while (attempts < 3) { // Retry up to 3 times
	        try {
	            // Wait for the Edit button to be visible
	            waitForVisibility(editButton, driver);
	            // Wait for the Edit button to be clickable
	            waitForElementToBeClickable(driver, editButton);
	            // Attempt to click the Edit button
	            editButton.click();
	            System.out.println("Edit button clicked successfully.");
	            return; // Exit the method if the click is successful
	        } catch (ElementClickInterceptedException e) {
	            System.out.println("Attempt " + (attempts + 1) + ": Edit button click intercepted. Retrying...");
	            // Wait for a short duration before retrying
	            try {
	                Thread.sleep(1000); // Sleep for 1 second (adjust if necessary)
	            } catch (InterruptedException ie) {
	                Thread.currentThread().interrupt(); // Restore interrupted state
	                throw new RuntimeException("Thread interrupted during retry wait", ie);
	            }
	            attempts++;
	        }
	    }
	    // If all retries fail, throw an exception
	    throw new RuntimeException("Failed to click the Edit button after 3 attempts.");
	}
	
	//Selects the dropdown option based on the input value: 1 for the first option, 2 for the second, and so on
	public void selectOptionFromMedicalCardTypeDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(medicareCardTypeButton);
		medicareCardTypeButton.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}
	
	// Action method to enter and validate the Medicare card number
    public void enterMedicareCardNumber(String cardNumber) {
        // Validate that the card number contains only digits
        if (!cardNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Card number must contain only digits.");
        }

        // Assign the card number to the public static variable
        providedCardNumber = cardNumber;

        // Interact with the input field
        waitForVisibility(medicareCardNumberField, driver);
        medicareCardNumberField.clear();
        medicareCardNumberField.sendKeys(cardNumber);
    }
    
  //Selects the dropdown option based on the input value: 1 for the first option, 2 for the second, and so on
  	public void selectOptionFromMedicalExpiryMonthDropdown(int stepsToTraverse) throws InterruptedException {
  		// Wait for the input box to be visible
  		waitVisibilityElement(medicareExpiryMonthDropdown);
  		medicareExpiryMonthDropdown.click();
  		Actions actions = new Actions(driver);
  		for (int i = 0; i < stepsToTraverse; i++) {
  			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
  			BaseTest.sleepFor(700);
  		}
  		actions.sendKeys(Keys.ENTER).build().perform();
  		// hamburgerMenu.click();

  	}
  	
    //Selects the dropdown option based on the input value: 1 for the first option, 2 for the second, and so on
  	public void isApplicantClaimingRebateDropdownValues(int stepsToTraverse) throws InterruptedException {
  		// Wait for the input box to be visible
  		waitVisibilityElement(isApplicantClaimingRebateDropdown);
  		isApplicantClaimingRebateDropdown.click();
  		Actions actions = new Actions(driver);
  		for (int i = 0; i < stepsToTraverse; i++) {
  			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
  			BaseTest.sleepFor(700);
  		}
  		actions.sendKeys(Keys.ENTER).build().perform();
  		// hamburgerMenu.click();

  	}
  	
  //Selects the dropdown option based on the input value: 1 for the first option, 2 for the second, and so on
  	public void isAllPeopleHoldPolicyDropdownValues(int stepsToTraverse) throws InterruptedException {
  		// Wait for the input box to be visible
  		waitVisibilityElement(IsAllPeopleholdPolicy);
  		IsAllPeopleholdPolicy.click();
  		Actions actions = new Actions(driver);
  		for (int i = 0; i < stepsToTraverse; i++) {
  			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
  			BaseTest.sleepFor(700);
  		}
  		actions.sendKeys(Keys.ENTER).build().perform();
  		// hamburgerMenu.click();

  	}
  	
 // Action Method: Enter future year
    public void enterMedicareCardExpiryYear(String year) {
        int yearAsInt = Integer.parseInt(year);
        if (yearAsInt < java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Past years are not allowed: " + year);
        }
        providedYear = yearAsInt; // Store year in the class-level variable
        medicareCardExpiryYearField.clear();
        medicareCardExpiryYearField.sendKeys(year);
    }
}




