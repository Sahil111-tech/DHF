package com.qa.airteam.pages;

import java.time.Year;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.BaseTest;

public class MedicareDetailsPage extends BaseTest {

	// constructor for initialization page factory and drivers

	public MedicareDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	// FindBy XPaths
	// Common locator for Yes and No buttons
	@FindBy(xpath = "//div[@name='isEntitled']/div/label/span[text()='Yes']")
	private WebElement medicareEligibilityYesOption;

	@FindBy(xpath = "//div[@name='isEntitled']/div/label/span[text()='No']")
	private WebElement medicareEligibilityNoOption;

	@FindBy(xpath = "//input[@name='cardNumber']")
	private WebElement cardNumberInputField;

	@FindBy(xpath = "//input[@name='cardReferenceNumber']")
	private WebElement cardReferenceNumberField;

	// Locator for Month field
	@FindBy(xpath = "//input[@name='validToMonth']")
	private WebElement validToMonthField;

	// Locator for Year field
	@FindBy(xpath = "//input[@name='validToYear']")
	private WebElement validToYearField;

	// XPath for the "Yes" option radio button
	@FindBy(xpath = "//div[@name='claimRebate']/div/label/span/span[@class='ant-radio-inner']/ancestor::label/span[contains(text(), 'Yes')]")
	private WebElement claimRebateYesRadioButton;

	// XPath for the "No" option radio button
	@FindBy(xpath = "//div[@name='claimRebate']/div/label/span/span[@class='ant-radio-inner']/ancestor::label/span[contains(text(), 'No')]")
	private WebElement claimRebateNoRadioButton;

	// XPath for the "Yes" option radio button
	@FindBy(xpath = "//div[@name='isPolicyCovered']/div/label/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
	private WebElement policyCoveredYesRadioButton;

	// XPath for the "No" option radio button
	@FindBy(xpath = "//div[@name='isPolicyCovered']/div/label/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
	private WebElement policyCoveredNoRadioButton;

	// XPath for the checkbox element
	@FindBy(xpath = "//label[@class='css-1y6xj52']/div")
	private WebElement checkboxDeclaration;

	// Radio buttons for "Yes" and "No"
	@FindBy(xpath = "//div[@name='isNominatePerson']/div/label[1]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
	private WebElement isNominatedRadioButtonYes;

	@FindBy(xpath = "//div[@name='isNominatePerson']/div/label[2]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
	private WebElement isNominatedRadioButtonNo;

	// Radio buttons for "Yes" and "No" under isAvantGroupMember
	@FindBy(xpath = "//div[@name='isAvantGroupMember']/div/label[1]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
	private WebElement yesAvantGroupMemberRadioButton;

	@FindBy(xpath = "//div[@name='isAvantGroupMember']/div/label[2]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
	private WebElement noAvantGroupMemberRadioButton;

	// Dropdown for 'howDidYouHearAboutUs'
	@FindBy(xpath = "//select[@name='howDidYouHearAboutUs']")
	private WebElement howDidYouHearAboutUsDropdown;

	// Radio button for Yes (Would you like us to check in with you after you join?)
	@FindBy(xpath = "//div[@name='getInTouch']/div/label[1]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
	private WebElement yesCheckInRadioButton;

	// Radio button for No (Would you like us to check in with you after you join?)
	@FindBy(xpath = "//div[@name='getInTouch']/div/label[2]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
	private WebElement noCheckInRadioButton;

	// Checkbox for "I have read and agree to the Terms and Conditions of Service."
	@FindBy(xpath = "//input[@type='checkbox' and @data-id='acceptTermsAndConditions']/..")
	private WebElement termsAndConditionsCheckbox;

	// Checkbox for "I agree to The Doctors' Health Fund using my personal
	// information in accordance with its Privacy Policy providing information and
	// marketing material by phone, text message, mail or email."
	@FindBy(xpath = "//label[@for='agreeUsingPersonalInformation']")
	private WebElement agreeUsingPersonalInformationCheckbox;

	// Checkbox for "I have read and agree to the AHSA Privacy Policy."
	@FindBy(xpath = "//input[@type='checkbox' and @data-id='acceptAHSA']/..")
	private WebElement ahsaPrivacyPolicyCheckbox;

	// Checkbox for "The information entered in this application is true and correct
	// and I confirm I am eligible to join Doctors' Health Fund."
	@FindBy(xpath = "//label[@for='confirmApplicationIsTrue']")
	private WebElement confirmApplicationIsTrueCheckbox;

	// Locate the BSB input field
	@FindBy(xpath = "//input[@name='bsb']")
	private WebElement bsbInputField;

	@FindBy(xpath = "//div[text()='Continue']/../../..//button/..")
	private WebElement submitAdditionalDetail;

	@FindBy(xpath = "//input[@name='accountNumber']")
	private WebElement accountNumberInputField;

	@FindBy(xpath = "//input[@name='accountName']")
	private WebElement accountNameInputField;

	@FindBy(xpath = "//input[@type='checkbox' and @data-id='directDebitAgreements']/..")
	private WebElement directDebitCheckbox;

	@FindBy(xpath = "//div[text()='Submit form']/../..")
	private WebElement submitFormButton;

	@FindBy(xpath = "//label[@for='useBankAccountDetails']")
	private WebElement useBankAccountDetailsCheckbox;
	
	@FindBy(xpath = "//div[text()='Claim payments']/..")
    private WebElement claimPaymentsButton;

	/* Action Methods: */

	public void selectMedicareEligibility(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			medicareEligibilityYesOption.click();
		} else if (option.equalsIgnoreCase("No")) {
			medicareEligibilityNoOption.click();
		} else {
			throw new IllegalArgumentException("Invalid option provided: " + option);
		}
	}

	public void enterCardNumber(String cardNumber) {
		cardNumberInputField.clear();
		cardNumberInputField.sendKeys(cardNumber);
	}

	public void enterCardReferenceNumber(String number) {
		// Validate input
		if (!number.matches("[1-9]")) {
			throw new IllegalArgumentException("Invalid Card Reference Number. Only values 1 to 9 are allowed.");
		}
		cardReferenceNumberField.clear(); // Clear the field before entering data
		cardReferenceNumberField.sendKeys(number); // Input the value
	}

	public void enterValidityDetails(String month, String year) {
		// Validate the month (1-12)
		if (!month.matches("^(0?[1-9]|1[0-2])$")) {
			throw new IllegalArgumentException("Invalid Month. Please enter a value between 1 and 12.");
		}

		// Validate the year (future years only)
		int currentYear = Year.now().getValue();
		int yearValue = Integer.parseInt(year);
		if (yearValue <= currentYear) {
			throw new IllegalArgumentException("Invalid Year. Please enter a future year.");
		}

		// Enter the month
		validToMonthField.clear();
		validToMonthField.sendKeys(month);

		// Enter the year
		validToYearField.clear();
		validToYearField.sendKeys(year);
	}

	// Action method to select the rebate option ("Yes" or "No")
	public void selectRebateOption(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			if (!claimRebateYesRadioButton.isSelected()) {
				claimRebateYesRadioButton.click();
			}
		} else if (option.equalsIgnoreCase("No")) {
			if (!claimRebateNoRadioButton.isSelected()) {
				claimRebateNoRadioButton.click();
			}
		} else {
			throw new IllegalArgumentException("Invalid option: " + option + ". Please provide 'Yes' or 'No'.");
		}
	}

	/**
	 * Step to select the policy coverage option.
	 * 
	 * @param option The option to select ("Yes" or "No").
	 */
	public void selectPolicyCoverageOption(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			if (!policyCoveredYesRadioButton.isSelected()) {
				policyCoveredYesRadioButton.click();
			}
		} else if (option.equalsIgnoreCase("No")) {
			if (!policyCoveredNoRadioButton.isSelected()) {
				policyCoveredNoRadioButton.click();
			}
		} else {
			throw new IllegalArgumentException("Invalid option: " + option + ". Please provide 'Yes' or 'No'.");
		}
	}

	/**
	 * Toggles the checkbox state based on the input.
	 * 
	 * @param shouldSelect true to select the checkbox, false to deselect it.
	 */
	public void toggleCheckbox(boolean shouldSelect) {
		if (shouldSelect && !checkboxDeclaration.isSelected()) {
			checkboxDeclaration.click();
		} else if (!shouldSelect && checkboxDeclaration.isSelected()) {
			checkboxDeclaration.click();
		}
	}

	// Action method to select "Yes" or "No" based on input
	public void selectRadioButton(String choice) {
		if (choice.equalsIgnoreCase("Yes")) {
			isNominatedRadioButtonYes.click();
		} else if (choice.equalsIgnoreCase("No")) {
			isNominatedRadioButtonNo.click();
		} else {
			throw new IllegalArgumentException("Invalid choice. Please select 'Yes' or 'No'.");
		}
	}

	// Action method to select 'Yes' or 'No' based on the input
	public void selectAvantGroupMemberRadioButton(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			yesAvantGroupMemberRadioButton.click();
		} else if (option.equalsIgnoreCase("No")) {
			noAvantGroupMemberRadioButton.click();
		} else {
			throw new IllegalArgumentException("Invalid option. Please select either 'Yes' or 'No'");
		}

	}

	// Action method to select a value from the dropdown
	public void selectHowDidYouHearAboutUsOption(String option) {
		Select select = new Select(howDidYouHearAboutUsDropdown);
		// Check if option exists and select it
		if (select.getOptions().stream().anyMatch(o -> o.getText().equals(option))) {
			select.selectByVisibleText(option);
		} else {
			throw new IllegalArgumentException("The provided option is not available in the dropdown: " + option);
		}
	}

	// Action method to select Yes or No based on input for "Would you like us to
	// check in with you after you join?"
	public void selectCheckInRadioButton(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			yesCheckInRadioButton.click();
		} else if (option.equalsIgnoreCase("No")) {
			noCheckInRadioButton.click();
		} else {
			throw new IllegalArgumentException("Invalid option provided. Please select 'Yes' or 'No'.");
		}
	}

	// Action method to select or deselect the "I have read and agree to the Terms
	// and Conditions of Service."
	public void selectTermsAndConditionsCheckbox(boolean select) {
		if (select && !termsAndConditionsCheckbox.isSelected()) {
			termsAndConditionsCheckbox.click();
		} else if (!select && termsAndConditionsCheckbox.isSelected()) {
			termsAndConditionsCheckbox.click();
		}
	}

	// Action method to select or deselect "I agree to The Doctors' Health Fund
	// using my personal information in accordance with its Privacy Policy."
	public void selectAgreeUsingPersonalInformationCheckbox(boolean select) {
		if (select && !agreeUsingPersonalInformationCheckbox.isSelected()) {
			agreeUsingPersonalInformationCheckbox.click();
		} else if (!select && agreeUsingPersonalInformationCheckbox.isSelected()) {
			agreeUsingPersonalInformationCheckbox.click();
		}
	}

	// Action method to select or deselect "I have read and agree to the AHSA
	// Privacy Policy."
	public void selectAhsaPrivacyPolicyCheckbox(boolean select) {
		if (select && !ahsaPrivacyPolicyCheckbox.isSelected()) {
			ahsaPrivacyPolicyCheckbox.click();
		} else if (!select && ahsaPrivacyPolicyCheckbox.isSelected()) {
			ahsaPrivacyPolicyCheckbox.click();
		}
	}

	// Action method to select or deselect "The information entered in this
	// application is true and correct and I confirm I am eligible to join Doctors'
	// Health Fund."
	public void selectConfirmApplicationIsTrueCheckbox(boolean select) {
		if (select && !confirmApplicationIsTrueCheckbox.isSelected()) {
			confirmApplicationIsTrueCheckbox.click();
		} else if (!select && confirmApplicationIsTrueCheckbox.isSelected()) {
			confirmApplicationIsTrueCheckbox.click();
		}
	}

	// Action method to enter BSB number
	public void enterBSB(String bsbNumber) {
		if (bsbNumber.matches("\\d{3}-\\d{3}")) { // Validate BSB format
			bsbInputField.clear();
			bsbInputField.sendKeys(bsbNumber);
		} else {
			throw new IllegalArgumentException("Invalid BSB format. Expected format: XXX-XXX.");
		}
	}

	// Action method to click the "Continue" button
	public void clickOnSubmitAdditionalDetail() throws InterruptedException {
		if (submitAdditionalDetail.isDisplayed() && submitAdditionalDetail.isEnabled()) {
			submitAdditionalDetail.click();
			Thread.sleep(2000);
			submitAdditionalDetail.click();
		} else {
			throw new IllegalStateException("The 'Continue' button is not clickable.");
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

		Random random = new Random(); // Create a Random instance

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
	
	  // Action method to click on the Claim Payments button
    public void clickClaimPaymentsButton() {
        claimPaymentsButton.click();
    }
	
	
	
	  // Action method for double-clicking the checkbox public void
	 public void toggleCheckboxByDoubleClick() throws InterruptedException {
	  
			/*
			 * Actions actions = new Actions(driver); // Double-click to uncheck
			 * actions.doubleClick(useBankAccountDetailsCheckbox).perform(); // Double-click
			 * again to re-check
			 * actions.doubleClick(useBankAccountDetailsCheckbox).perform();
			 */
	useBankAccountDetailsCheckbox.click();
    Thread.sleep(700);
    useBankAccountDetailsCheckbox.click();
	
	  }
	 
}
