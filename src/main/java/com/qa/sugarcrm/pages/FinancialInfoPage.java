package com.qa.sugarcrm.pages;

import java.time.Duration;
import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;

public class FinancialInfoPage extends BaseTest {
	protected WebDriver driver;
	protected static String getBSBNumber;
	protected static String getBenefitBSBNumber;
	public static String permanentCRN;
	public static String tempCRN;
	public FinancialInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for Financial Info Tab
	@FindBy(xpath = "//span[text()='Financial Info']")
	public static WebElement financialInfoTab;

	// To click on Group dropdown
	@FindBy(xpath = "//span[@data-fieldname='grp_group_leads_1_name']")
	public static WebElement groupDropdown;

	// To input the text into Group dropdown
	@FindBy(xpath = "(//label[starts-with(@for,'s2id_autogen')])[101]/following-sibling::input") /*
																									 * "((//label[starts-with(@for,'s2id_autogen')])/following-sibling::input[@class='select2-input'])[52]")
																									 */
	public static WebElement inputTextIntoGroupDropdown;

	@FindBy(xpath = "//span[@class='h-full w-15']")
	private WebElement hamburgerMenu;

	@FindBy(xpath = "//span[@data-fieldname='debit_account_type_c']")
	private WebElement debitAccountType;

	@FindBy(xpath = "//input[@name='debit_bank_account_name_c']")
	private WebElement debitBankAccountNameInput;

	@FindBy(xpath = "//input[@name='debit_bank_bsb_no_c']")
	private WebElement debitBankBSBField;

	@FindBy(xpath = "//input[@name='debit_bank_account_no_c']")
	private WebElement debitBankAccountNumberInput;

	// Locate Benefit Bank Account Name input field
	@FindBy(xpath = "//input[@name='credit_bank_account_name_c']")
	private WebElement benifitBankAccountNameInput;

	// Locate Benefit BSB Name input field
	@FindBy(xpath = "//input[@name='credit_bank_bsb_no_c']")
	private WebElement benifitBSBInputField;

	// Locate Benefit bank account number field
	@FindBy(xpath = "//input[@name='credit_bank_account_no_c']")
	private WebElement benifitBankAccountNumberInputField;

	// Locate the Direct Debit Start Date field
	@FindBy(xpath = "//input[@name='direct_debit_start_date_c']")
	private WebElement directDebitStartDateField;

	// to enter the card number
	@FindBy(xpath = "//input[@name='cardno_c']")
	private WebElement cardNumberField;

	// to enter the card expiry month
	@FindBy(xpath = "//span[@data-fieldname='expiry_month_c']")
	private WebElement cardExpiryMonthField;

	// to enter the card holder name
	@FindBy(xpath = "//input[@name='card_holder_name_c']")
	private WebElement cardHolderNameField;

	// to enter the card expiry year
	@FindBy(xpath = "//input[@name='expiry_year_c']")
	private WebElement expiryYearField;

	@FindBy(xpath = "//div[@class='record-link-wrapper span4']//span[@data-fieldname='temp_crn_number_c']//span//div")
	private WebElement tempCRNNumberField;

	@FindBy(xpath = "(//div[@class='btn-toolbar pull-right']//a[@name='save_button'])[2]")
	private WebElement saveCreditCardDetailsButton;

	@FindBy(xpath = "//a[text()='Save']")
	private WebElement saveFinancialInformationButton;
	
	@FindBy(xpath = "//div[@class='record-link-wrapper span4']//span[@data-fieldname='crn_number_c']//span//div") //(//div[@class='ellipsis_inline' and @data-original-title])[6] ////span[@data-fieldname='crn_number_c']//div[@class='ellipsis_inline']
	private WebElement permanentCRNNumberField;

	/***************************
	 * Actions Method
	 *********************************************************/

	// Method to click on the Financial tab
	public void clickFinancialTab() {
		waitForElementToBeClickable(driver, financialInfoTab);
		financialInfoTab.click();
	}

	public void handleGroupDropdownWithKeyboard(String inputText, int stepsToTraverse, WebDriver driver)
			throws InterruptedException {
		groupDropdown.click();
		waitForVisibility(inputTextIntoGroupDropdown, driver);
		// inputTextIntoGroupDropdown.clear();
		inputTextIntoGroupDropdown.sendKeys(inputText);
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}

		actions.sendKeys(Keys.ENTER).build().perform();

	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	public void selectOptionFromDebitAccountTypeDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(debitAccountType);
		debitAccountType.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Method to input text into the Debit Bank Account Name field
	public void enterDebitBankAccountName(String accountName) {
		waitForVisibility(debitBankAccountNameInput, driver);
		debitBankAccountNameInput.clear();
		debitBankAccountNameInput.sendKeys(accountName);
	}

	// Method to input a random Australian name in the Debit Bank Account Name field
	public void enterRandomDebitBankAccountName() {
		String randomName = BaseTest.getRandomAustralianName(); // Get a random name
		waitForVisibility(debitBankAccountNameInput, driver);
		debitBankAccountNameInput.clear();
		debitBankAccountNameInput.sendKeys(randomName);
		System.out.println("Entered Random Name: " + randomName);
	}

	// Action method to enter BSB number
	public void enterBSB(String bsbNumber) {

		if (bsbNumber.matches("\\d{3}-\\d{3}")) { // Validate BSB format
			debitBankBSBField.clear();
			debitBankBSBField.sendKeys(bsbNumber);
		} else {
			throw new IllegalArgumentException("Invalid BSB format. Expected format: XXX-XXX.");
		}
		getBSBNumber = bsbNumber;
	}

	// Method to input a bank account number
	public void enterDebitBankAccountNumber(String accountNumber) {
		if (accountNumber.length() > 20) {
			throw new IllegalArgumentException("Account number exceeds maximum limit of 20 digits.");
		}
		waitForVisibility(debitBankAccountNumberInput, driver);
		debitBankAccountNumberInput.clear();
		debitBankAccountNumberInput.sendKeys(accountNumber);
		System.out.println("Entered Account Number: " + accountNumber);
	}

	// Method to input a random Australian name in the Debit Bank Account Name field
	public void enterRandomBenefitBankAccountName() {
		String randomName = BaseTest.getRandomAustralianName(); // Get a random name
		waitForVisibility(benifitBankAccountNameInput, driver);
		benifitBankAccountNameInput.clear();
		benifitBankAccountNameInput.sendKeys(randomName);
		System.out.println("Entered Random Name: " + randomName);
	}

	// Action method to enter BSB number in Benefit
	public void enterBSBInBenefitInputField(String bsbNumber) {

		if (bsbNumber.matches("\\d{3}-\\d{3}")) { // Validate BSB format
			benifitBSBInputField.clear();
			benifitBSBInputField.sendKeys(bsbNumber);
		} else {
			throw new IllegalArgumentException("Invalid BSB format. Expected format: XXX-XXX.");
		}
		getBenefitBSBNumber = bsbNumber;
	}

	// Method to input a Benefit bank account number
	public void enterBenefitBankAccountNumber(String accountNumber) {
		if (accountNumber.length() > 20) {
			throw new IllegalArgumentException("Account number exceeds maximum limit of 20 digits.");
		}
		waitForVisibility(benifitBankAccountNumberInputField, driver);
		benifitBankAccountNumberInputField.clear();
		benifitBankAccountNumberInputField.sendKeys(accountNumber);
		System.out.println("Entered Account Number: " + accountNumber);
	}

	// Action method for the direct debit
	public void enterStartDate(String date) {
		// Regular expression to validate DD/MM/YYYY format
		String regex = "^\\d{2}/\\d{2}/\\d{4}$";

		if (date.matches(regex)) {
			// Enter the valid date
			directDebitStartDateField.clear();
			directDebitStartDateField.sendKeys(date);
			log.info("Entered date in the correct format: " + date);
		} else {
			throw new IllegalArgumentException("Invalid date format. Please use DD/MM/YYYY.");
		}
	}

	public void enterCardNumber(String cardNumber) {
		// Regular expression to validate only digits
		String regex = "^\\d+$";

		if (cardNumber.matches(regex)) {
			// Enter the valid card number
			cardNumberField.clear();
			cardNumberField.sendKeys(cardNumber);
			log.info("Entered card number: " + cardNumber);
		} else {
			throw new IllegalArgumentException("Invalid card number. Only digits are allowed.");
		}
	}

	public void selectOptionFromCardExpiryMonthDropdown(int stepsToTraverse) throws InterruptedException {

		// Wait for the dropdown to be visibiloty
		waitForVisibility(cardExpiryMonthField, driver);

		// Click to open the dropdown
		cardExpiryMonthField.click();

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

	public void enterCardHolderName() {
		// Predefined list first and last names
		String[] firstNames = { "Oliver", "George", "Harry", "Jack", "Charlie", "Jacob", "Thomas", "Henry", "William",
				"James" };
		String[] lastNames = { "Smith", "Jones", "Taylor", "Brown", "Williams", "Wilson", "Johnson", "Evans", "Thomas",
				"Roberts" };

		// Generate random indices
		Random random = new Random();
		String firstName = firstNames[random.nextInt(firstNames.length)];
		String lastName = lastNames[random.nextInt(lastNames.length)];

		// Combine first and last names
		String fullName = firstName + " " + lastName;

		// Send the generated name to the input field
		cardHolderNameField.clear();
		cardHolderNameField.sendKeys(fullName);
		log.info("Entered cardholder name: " + fullName);
	}

	public void enterExpiryYear(String expiryYear) {
		// Get the current year
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		// Parse the input year to validate it's a future year
		int enteredYear = Integer.parseInt(expiryYear);

		// Check if the year is valid (future year and digits only)
		if (enteredYear > currentYear) {
			expiryYearField.clear();
			expiryYearField.sendKeys(expiryYear);
			log.info("Entered expiry year: " + expiryYear);
		} else {
			throw new IllegalArgumentException(
					"Invalid expiry year provided: " + expiryYear + ". It must be a future year.");
		}
	}

	public String verifyAndCaptureTempCRNNumber() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Wait for the element to be visible
		WebElement tempCRNElement = wait.until(ExpectedConditions.visibilityOf(tempCRNNumberField));

		// Retrieve the value of the 'data-original-title' attribute
		tempCRN = tempCRNElement.getAttribute("title");

		// Validate that the attribute is not null or empty
		if (tempCRN == null || tempCRN.isEmpty()) {
			throw new AssertionError("The 'data-original-title' attribute is empty or null.");
		}

		// Log or return the value for further use
		log.info("Temporary CRN Number captured: " + tempCRN);
		
		System.out.println("Validation Passed: Temporary CRN Number captured and contains value = " + tempCRN + " (not null or empty).");

		return tempCRN;
	}

	public void clickSaveCreditCardDetailsButton() {
		if (saveCreditCardDetailsButton.isDisplayed() && saveCreditCardDetailsButton.isEnabled()) {
			saveCreditCardDetailsButton.click();
			log.info("Successfully submitted and saved the credit card details.");
		} else {
			throw new AssertionError("Save button for credit card details is not visible or enabled.");
		}
	}

	public void clickSaveFinancialInformationButton() {
		if (saveFinancialInformationButton.isDisplayed() && saveFinancialInformationButton.isEnabled()) {
			saveFinancialInformationButton.click();
			log.info("I successfully saved the Financial Information.");
		} else {
			throw new AssertionError("Save button for Financial Information is not visible or enabled.");
		}
	}
	
	
	public String verifyAndPermanentCRNNumber() {
		
        waitForVisibilityUntil(permanentCRNNumberField, 15, driver);
		// Retrieve the value of the 'data-original-title' attribute
		permanentCRN = permanentCRNNumberField.getAttribute("title");

		// Validate that the attribute is not null or empty
		if (permanentCRN == null || permanentCRN.isEmpty()) {
			throw new AssertionError("The 'data-original-title' attribute is empty or null.");
		}

		// Log or return the value for further use
		log.info("Permanent CRN Number captured: " + permanentCRN);
		System.out.println("Validation Passed:Permanent CRN Number captured and contains value = " + permanentCRN + " (not null or empty).");

	    
		return permanentCRN;
	}
	 
	
}
