package com.qa.airteam.pages;

import java.time.Year;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
	@FindBy(xpath = "//input[@name='validToDay']")
	private WebElement validToDayField;
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

	// XPath locators using FindBy
	@FindBy(xpath = "//input[@value='green']")
	private WebElement greenCardRadioButton;

	@FindBy(xpath = "//input[@value='blue']")
	private WebElement blueCardRadioButton;

	@FindBy(xpath = "//img[@srcset='/images/medicare-card-green@1x.png 1x,/images/medicare-card-green@2x.png 2x']")
	private WebElement greenCardImage;

	@FindBy(xpath = "//img[@srcset='/images/medicare-card-blue@1x.png 1x,/images/medicare-card-blue@2x.png 2x']")
	private WebElement blueCardImage;

	/********************* Action Methods: *******************************/
	// Actions method for: Are all the people on this policy eligible for Medicare?
	public void selectMedicareEligibility(String option) {
		log.info("Selecting Medicare eligibility option: " + option);
		if (option.equalsIgnoreCase("Yes")) {
			medicareEligibilityYesOption.click();
			log.info("Selected 'Yes' for Medicare eligibility.");
		} else if (option.equalsIgnoreCase("No")) {
			medicareEligibilityNoOption.click();
			log.info("Selected 'No' for Medicare eligibility.");
		} else {
			log.error("Invalid option provided for Medicare eligibility: " + option);
			throw new IllegalArgumentException("Invalid option provided: " + option);
		}
	}

	// Method to verify images for green and blue Medicare options
	public void verifyImagesForGreenAndBlueOptions() {
		try {
			// Validate green card image
			log.info("Selecting the green card option...");
			greenCardRadioButton.click();
			waitForVisibility(greenCardImage, driver);
			Assert.assertTrue(greenCardImage.isDisplayed(), "Green card image is not displayed");
			log.info("Green card image is successfully displayed!");

			// Validate blue card image
			log.info("Selecting the blue card option...");
			blueCardRadioButton.click();
			waitForVisibility(blueCardImage, driver);
			Assert.assertTrue(blueCardImage.isDisplayed(), "Blue card image is successfully displayed!");
			log.info("Blue card image is successfully displayed!");
		} catch (AssertionError e) {
			log.error("Assertion failed: " + e.getMessage());
			throw e; // Re-throw to ensure test fails
		} catch (Exception e) {
			log.error("An error occurred during validation: " + e.getMessage());
			throw e;
		}
	}

	// Action method to enter the card number in the "Card Number" input field
	public void enterCardNumber(String cardNumber) {
		log.info("Entering card number: " + cardNumber);
		cardNumberInputField.clear();
		cardNumberInputField.sendKeys(cardNumber);
		log.info("Card number entered successfully.");
	}

	// Action method to enter the value in the input field: "Card reference number"
	public void enterCardReferenceNumber(String number) {
		log.info("Entering card reference number: " + number);
		if (!number.matches("[1-9]")) {
			log.error("Invalid Card Reference Number: " + number + ". Only values 1 to 9 are allowed.");
			throw new IllegalArgumentException("Invalid Card Reference Number. Only values 1 to 9 are allowed.");
		}
		cardReferenceNumberField.clear();
		cardReferenceNumberField.sendKeys(number);
		log.info("Card reference number entered successfully.");
	}

	// Action methods to enter the date and year in the field "Valid to"
	public void enterValidityDetails(String day, String month, String year) {
	    log.info("Entering validity details: Day = " + day + ", Month = " + month + ", Year = " + year);

	    // Validate day
	    if (!day.matches("^(0?[1-9]|[12][0-9]|3[01])$")) {
	        log.error("Invalid Day: " + day + ". Please enter a value between 1 and 31.");
	        throw new IllegalArgumentException("Invalid Day. Please enter a value between 1 and 31.");
	    }

	    // Validate month
	    if (!month.matches("^(0?[1-9]|1[0-2])$")) {
	        log.error("Invalid Month: " + month + ". Please enter a value between 1 and 12.");
	        throw new IllegalArgumentException("Invalid Month. Please enter a value between 1 and 12.");
	    }

	    // Validate year
	    int currentYear = Year.now().getValue();
	    int yearValue = Integer.parseInt(year);
	    if (yearValue <= currentYear) {
	        log.error("Invalid Year: " + year + ". Please enter a future year.");
	        throw new IllegalArgumentException("Invalid Year. Please enter a future year.");
	    }

	    // Enter Day into the validToDayField
	    validToDayField.clear();
	    validToDayField.sendKeys(day);
	    log.info("Day entered successfully: " + day);

	    // Enter Month and Year (same as before)
	    validToMonthField.clear();
	    validToMonthField.sendKeys(month);
	    log.info("Month entered successfully: " + month);

	    validToYearField.clear();
	    validToYearField.sendKeys(year);
	    log.info("Year entered successfully: " + year);
	}
	// Action method to select the rebate option ("Yes" or "No")
	public void selectRebateOption(String option) {
		log.info("Selecting rebate option: " + option);
		if (option.equalsIgnoreCase("Yes")) {
			if (!claimRebateYesRadioButton.isSelected()) {
				claimRebateYesRadioButton.click();
				log.info("Selected 'Yes' for rebate option.");
			}
		} else if (option.equalsIgnoreCase("No")) {
			if (!claimRebateNoRadioButton.isSelected()) {
				claimRebateNoRadioButton.click();
				log.info("Selected 'No' for rebate option.");
			}
		} else {
			log.error("Invalid rebate option provided: " + option);
			throw new IllegalArgumentException("Invalid option: " + option + ". Please provide 'Yes' or 'No'.");
		}
	}

	/**
	 * Step to select the policy coverage option.@param option The option to select
	 * ("Yes" or "No").
	 **/
	public void selectPolicyCoverageOption(String option) {
		log.info("Selecting policy coverage option: " + option);
		if (option.equalsIgnoreCase("Yes")) {
			if (!policyCoveredYesRadioButton.isSelected()) {
				policyCoveredYesRadioButton.click();
				log.info("Selected 'Yes' for policy coverage.");
			}
		} else if (option.equalsIgnoreCase("No")) {
			if (!policyCoveredNoRadioButton.isSelected()) {
				policyCoveredNoRadioButton.click();
				log.info("Selected 'No' for policy coverage.");
			}
		} else {
			log.error("Invalid policy coverage option: " + option);
			throw new IllegalArgumentException("Invalid option: " + option + ". Please provide 'Yes' or 'No'.");
		}
	}

	/**
	 * Toggles the checkbox state based on the input @param shouldSelect true to
	 * select the checkbox, false to deselect it.
	 **/
	public void toggleCheckbox(boolean shouldSelect) {
		log.info("Toggling checkbox to " + (shouldSelect ? "select" : "deselect") + " state.");
		if (shouldSelect && !checkboxDeclaration.isSelected()) {
			checkboxDeclaration.click();
			log.info("Checkbox selected successfully.");
		} else if (!shouldSelect && checkboxDeclaration.isSelected()) {
			checkboxDeclaration.click();
			log.info("Checkbox deselected successfully.");
		}
	}

	// Action method to click on the Claim Payments button
	public void clickClaimPaymentsButton() {
		log.info("Clicking the Claim Payments button.");
		claimPaymentsButton.click();
		log.info("Claim Payments button clicked successfully.");
	}

	// Action method to click "Continue radio button"
	public WebElement clickContinueButton() throws InterruptedException {
		log.info("Clicking the Continue button.");
		waitForVisibility(clickContinueButton, driver);
		waitForElementToBeClickable(driver, clickContinueButton);
		Thread.sleep(2000);
		click(clickContinueButton);
		log.info("Continue button clicked successfully.");
		return clickContinueButton;
	}

}
