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

	// xpath for "Yes" selection: Are all the people on this policy eligible for
	// Medicare?
	@FindBy(xpath = "//div[@name='isEntitled']/div/label/span[text()='Yes']")
	private WebElement medicareEligibilityYesOption;

	// xpath for "No" selection: Are all the people on this policy eligible for
	// Medicare?
	@FindBy(xpath = "//div[@name='isEntitled']/div/label/span[text()='No']")
	private WebElement medicareEligibilityNoOption;

	// xpath to enter the card number
	@FindBy(xpath = "//input[@name='cardNumber']")
	private WebElement cardNumberInputField;

	// xpath to enter the card number
	@FindBy(xpath = "//input[@name='cardReferenceNumber']")
	private WebElement cardReferenceNumberField;

	// xpath to enter the Month field
	@FindBy(xpath = "//input[@name='validToMonth']")
	private WebElement validToMonthField;

	// xpath to enter the Year field
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

	/*
	 * //xpath to click on Continue button
	 * 
	 * @FindBy(xpath = "//div[text()='Continue']/../../..//button/..") private
	 * WebElement submitAdditionalDetail;
	 */

	// Do you want to claim the Australian Government Rebate on Private Health
	// Insurance to reduce the price you pay for your policy?
	@FindBy(xpath = "//div[text()='Claim payments']/..")
	private WebElement claimPaymentsButton;

	// Locator for continue button
	@FindBy(xpath = "//div[text()='Continue']/../../..//button/..") // "//div[@class='css-159nnwp']//div[@class='css-1p15x7ze18zzrqo1']"
	protected WebElement clickContinueButton;

	/********************* Action Methods: *******************************/
	// Actions method for: Are all the people on this policy eligible for Medicare?
	public void selectMedicareEligibility(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			medicareEligibilityYesOption.click();
		} else if (option.equalsIgnoreCase("No")) {
			medicareEligibilityNoOption.click();
		} else {
			throw new IllegalArgumentException("Invalid option provided: " + option);
		}
	}

	// Action method to enter the card number in the "Card Number" input field
	public void enterCardNumber(String cardNumber) {
		cardNumberInputField.clear();
		cardNumberInputField.sendKeys(cardNumber);
	}

	// Action method to enter the value in the input field: "Card reference number"
	public void enterCardReferenceNumber(String number) {
		// Validate input
		if (!number.matches("[1-9]")) {
			throw new IllegalArgumentException("Invalid Card Reference Number. Only values 1 to 9 are allowed.");
		}
		cardReferenceNumberField.clear(); // Clear the field before entering data
		cardReferenceNumberField.sendKeys(number); // Input the value
	}

	// Action methods to enter the date and year in the field "Valid to"
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
	 * Step to select the policy coverage option.@param option The option to select
	 * ("Yes" or "No").
	 **/
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
	 * Toggles the checkbox state based on the input @param shouldSelect true to
	 * select the checkbox, false to deselect it.
	 **/
	public void toggleCheckbox(boolean shouldSelect) {
		if (shouldSelect && !checkboxDeclaration.isSelected()) {
			checkboxDeclaration.click();
		} else if (!shouldSelect && checkboxDeclaration.isSelected()) {
			checkboxDeclaration.click();
		}
	}

	// Action method to click on the Claim Payments button
	public void clickClaimPaymentsButton() {
		claimPaymentsButton.click();
	}

	// Action method to click "Continue radio button"
	public WebElement clickContinueButton() throws InterruptedException {
		// waitVisibility(clickContinueRadioButton);
		waitForVisibility(clickContinueButton, driver);
		waitForElementToBeClickable(driver, clickContinueButton);
		Thread.sleep(2000);
		click(clickContinueButton);
		log.info("click on continue button");
		// Thread.sleep(2000);
		return clickContinueButton;
	}

}
