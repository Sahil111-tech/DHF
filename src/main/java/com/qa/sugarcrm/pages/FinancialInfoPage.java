package com.qa.sugarcrm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class FinancialInfoPage extends BaseTest {
	protected WebDriver driver;
	protected static String getBSBNumber;
	protected static String getBenefitBSBNumber;

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

}
