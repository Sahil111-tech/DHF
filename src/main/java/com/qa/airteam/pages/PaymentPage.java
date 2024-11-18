package com.qa.airteam.pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

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

	/*******************************
	 * Actions Methods
	 *********************************/

	// Action method to enter BSB number
	public void enterBSB(String bsbNumber) {
		if (bsbNumber.matches("\\d{3}-\\d{3}")) { // Validate BSB format
			bsbInputField.clear();
			bsbInputField.sendKeys(bsbNumber);
		} else {
			throw new IllegalArgumentException("Invalid BSB format. Expected format: XXX-XXX.");
		}
	}
	
	public void enterAccountNumber(String accountNumber) {
		if (accountNumber.matches("\\d{1,9}")) { // This ensures that only digits (1 to 9) are allowed.
			accountNumberInputField.clear();
			accountNumberInputField.sendKeys(accountNumber);
		} else {
			throw new IllegalArgumentException("Account number must contain only digits (max 9 digits).");
		}

	}
	
	// Method to generate and enter a random Australian account name
		public void enterRandomAccountName() {
			// Arrays defined locally within the method
			String[] FIRST_NAMES = { "Oliver", "Jack", "Noah", "William", "James", "Thomas", "Ethan", "Lucas", "Mason",
					"Charlie" };

			String[] LAST_NAMES = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Wilson", "Moore",
					"Taylor" };

			Random random = new Random(); 

			// Pick a random first and last name from the lists
			String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
			String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

			String accountName = firstName + " " + lastName; // Concatenate first and last name

			// Enter the generated name into the account name field
			accountNameInputField.clear();
			accountNameInputField.sendKeys(accountName);
		}
		
		// Action method to click direct debit checkbox
		public void clickDirectDebitCheckbox() {
			directDebitCheckbox.click();
		}
		
		// Method to click on the submit form button
		public void clickSubmitFormButton() throws InterruptedException {
			submitFormButton.click();
			Thread.sleep(700);
		}
		
		// Action method for double-clicking the checkbox: How would you like us to pay any claim benefits back to you?
		public void toggleCheckboxByDoubleClick() throws InterruptedException {
			useBankAccountDetailsCheckbox.click();
			Thread.sleep(1000);
			useBankAccountDetailsCheckbox.click();

		}


}
