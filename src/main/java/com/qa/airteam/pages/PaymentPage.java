package com.qa.airteam.pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class PaymentPage extends BaseTest {

	// constructor for initialization page factory and drivers
	public PaymentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/***** Covering here Payment details *****/

	// Locate the BSB input field
	@FindBy(xpath = "//input[@name='bsb']")
	private WebElement bsbInputField;
	
	// Locate the account number input field
	@FindBy(xpath = "//input[@name='accountNumber']")
	private WebElement accountNumberInputField;
	
	// Locate the account name input field
	@FindBy(xpath = "//input[@name='accountName']")
	private WebElement accountNameInputField;
	
   //Direct Debit Request Service Agreement.
	@FindBy(xpath = "//input[@type='checkbox' and @data-id='directDebitAgreements']/..")
	private WebElement directDebitCheckbox;
	
	//Locate the submit button.
	@FindBy(xpath = "//div[text()='Submit form']/../..")
	private WebElement submitFormButton;
	
	//Locate the checkbox:  How would you like us to pay any claim benefits back to you?
	@FindBy(xpath = "//label[@for='useBankAccountDetails']")
	private WebElement useBankAccountDetailsCheckbox;
	
	@FindBy(xpath = "//h2[text()='Thank you for joining']")
	protected WebElement thankYouMessage;

	/*******************************
	 * Actions Methods
	 *********************************/

	// Action method to enter BSB number
	public void enterBSB(String bsbNumber) {
		if (bsbNumber.matches("\\d{3}-\\d{3}")) { // Validate BSB format
	        log.info("Entering BSB number: " + bsbNumber);
	        bsbInputField.clear();
	        bsbInputField.sendKeys(bsbNumber);
	        log.info("BSB number entered successfully.");
	    } else {
	        log.error("Invalid BSB format: " + bsbNumber + ". Expected format: XXX-XXX.");
	        throw new IllegalArgumentException("Invalid BSB format. Expected format: XXX-XXX.");
	    }
	}
	
	public void enterAccountNumber(String accountNumber) {
		if (accountNumber.matches("\\d{1,9}")) { // Validate account number format
	        log.info("Entering account number: " + accountNumber);
	        accountNumberInputField.clear();
	        accountNumberInputField.sendKeys(accountNumber);
	        log.info("Account number entered successfully.");
	    } else {
	        log.error("Invalid account number format: " + accountNumber + ". Must contain only digits (max 9 digits).");
	        throw new IllegalArgumentException("Account number must contain only digits (max 9 digits).");
	    }
	}
	
	// Method to generate and enter a random Australian account name
		public void enterRandomAccountName() {
			 log.info("Generating a random Australian account name.");
			    String[] FIRST_NAMES = { "Oliver", "Jack", "Noah", "William", "James", "Thomas", "Ethan", "Lucas", "Mason", "Charlie" };
			    String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Wilson", "Moore", "Taylor" };
			    Random random = new Random();

			    String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
			    String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
			    String accountName = firstName + " " + lastName;

			    log.info("Generated account name: " + accountName);
			    accountNameInputField.clear();
			    accountNameInputField.sendKeys(accountName);
			    log.info("Random account name entered successfully.");
		}
		
		// Action method to click direct debit checkbox
		public void clickDirectDebitCheckbox() {
			log.info("Clicking the direct debit checkbox.");
		    directDebitCheckbox.click();
		    log.info("Direct debit checkbox clicked successfully.");
		}
		
		// Method to click on the submit form button
		public void clickSubmitFormButton() throws InterruptedException {
			log.info("Clicking the submit form button.");
		    submitFormButton.click();
		    Thread.sleep(700);
		    log.info("Submit form button clicked successfully.");
		}
		
		// Action method for double-clicking the checkbox: How would you like us to pay any claim benefits back to you?
		public void toggleCheckboxByDoubleClick() throws InterruptedException {
			log.info("Double-clicking the checkbox: 'How would you like us to pay any claim benefits back to you?'.");
		    useBankAccountDetailsCheckbox.click();
		    log.info("First click on the checkbox completed.");
		    Thread.sleep(1000);
		    useBankAccountDetailsCheckbox.click();
		    log.info("Second click on the checkbox completed. Checkbox toggled successfully.");
		}
		
		public boolean isThankYouMessageDisplayed() {
		    waitForVisibility(thankYouMessage, driver); // Ensure element is visible
		    return thankYouMessage.isDisplayed(); // Return true if the element is displayed
		}


}
