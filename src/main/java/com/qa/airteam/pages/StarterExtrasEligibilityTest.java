package com.qa.airteam.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.*;

public class StarterExtrasEligibilityTest extends BaseTest {
	// constructor for initialization page factory and drivers
	public StarterExtrasEligibilityTest(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

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

	// Locator to click on choose cover
	@FindBy(xpath = "//button[text()='Choose cover']")
	protected WebElement clickChooseCover;

	// Locator to click on apply now button
	@FindBy(xpath = "(//div[@class='button-with-icon css-zkfaav' and contains(text(), 'Apply now')])[3]")
	protected WebElement clickApplyNow;

	// "No" radio button for "Are you a current member of Doctors' Health Fund?"
	@FindBy(xpath = "//input[@class='ant-radio-input' and @type='radio' and @value='false']")
	WebElement memberStatusNoRadioBtn;

	// "Yes" radio button for "Are you an Australian citizen or permanent resident?"
	@FindBy(xpath = "(//input[@class='ant-radio-input' and @type='radio' and @value='true'])[2]")
	protected WebElement citizenshipYesRadioBtn;

	@FindBy(xpath = "//div[text()='Health practitioner']")
	protected WebElement clickHealthPractioner;

	@FindBy(xpath = "//div[text()='Medical practitioner']")
	protected WebElement clickMedicalPractioner;

	@FindBy(xpath = "//div[@class='ant-radio-group ant-radio-group-outline css-1ne8pr6 e7n18qt2']/div/div/div/label/span/input[@value='Dental']")
	protected WebElement dentalRadioButtons;

	@FindBy(xpath = "//div[@class='ant-radio-group ant-radio-group-outline css-1ne8pr6 e7n18qt2']/div/div/div/label/span/input[@value='Intern']")
	protected WebElement internRadioButtons;

	@FindBy(xpath = "//div[@class='ant-radio-group ant-radio-group-outline css-1ne8pr6 e7n18qt2']/div/div/div/label/span/input[@value='Specialist']")
	protected WebElement specialistRadioButtons;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Intern']")
	protected WebElement internRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Resident']")
	protected WebElement residentRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Registrar']")
	protected WebElement registrarRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='General Practitioner']")
	protected WebElement generalPractitionerRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Specialist']")
	protected WebElement specialistRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Career Medical Officer']")
	protected WebElement careerMedicalOfficerRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Retired doctor']")
	protected WebElement retiredDoctorRadioButton;

	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Other']")
	protected WebElement otherRadioButton;

	@FindBy(xpath = "//div[text()='Continue']/../../..//button/..") // "//div[@class='css-159nnwp']//div[@class='css-1p15x7ze18zzrqo1']"
	protected WebElement clickContinueButton;

	// to locate "Yes" and "No" radio buttons for the "Are you transferring from
	// another fund?"
	@FindBy(xpath = "//div[@name='isTransferringFromAnotherFund']//label//span[text()='Yes']")
	private WebElement yesRadioButton;

	@FindBy(xpath = "//div[@name='isTransferringFromAnotherFund']//label//span[text()='No']")
	private WebElement noRadioButton;

	@FindBy(xpath = "//div[text()='OK']/../../..//button") // div[@class='css-1bvpeds e18zzrqo1']
	public WebElement acceptAlert;

	@FindBy(xpath = "//div[text()='Next']/../../..//button") //// div[@class='css-qvm03q
																//// e18zzrqo1']/button[@class='css-1jlaai7']
	private WebElement nextButton;

	// To select user title Mr,Mrs,Dr, etc.
	@FindBy(xpath = "//select[@name='title']") //// div[starts-with(@class, 'e1yyxg3t0')]//select[@name='title']
	private WebElement userTitle;

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement firstNameInput;

	@FindBy(xpath = "//input[@name='middleName']")
	private WebElement middleNameInput;

	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement lastNameInput;

	// Using FindBy annotation to locate the Male, Female, and Other radio buttons
	@FindBy(xpath = "//div[@name='gender']/div/label/span[text()='Male']")
	protected WebElement maleRadioBtn;

	@FindBy(xpath = "//div[@name='gender']/div/label/span[text()='Female']")
	protected WebElement femaleRadioBtn;

	@FindBy(xpath = "//div[@name='gender']/div/label/span[text()='Other']")
	protected WebElement otherRadioBtn;

	@FindBy(xpath = "//input[@name='email']")
	protected WebElement emailInputBox;

	@FindBy(xpath = "//input[@name='phoneNumber']")
	protected WebElement phoneInputBox;

	@FindBy(xpath = "//div[@class='form-control css-1rcfaju ex1346n6']/following-sibling::div[2]/div[2]/input[@name='residentialAddress']")
	protected WebElement residentialAddressInputBox;
	// XPath for the Yes radio buttons for "Are you an existing Avant member?"
	@FindBy(xpath = "//div[@name='existingAvantMember']//div/label[1][span[contains(text(),'Yes')]]")
	private WebElement existingAvantMemberRadioButtonYes;

	// XPath for the No radio buttons for "Are you an existing Avant member?"
	@FindBy(xpath = "//div[@name='existingAvantMember']//div/label[2][span[contains(text(),'No')]]")
	private WebElement existingAvantMemberRadioButtonNo;

	// XPath for Submit Your Details Button
	@FindBy(xpath = "//div[@class='css-fbcqx2']/div[2]/button/div/div[text()='Continue']")
	private WebElement yourDetailsContinueButton;

	/***************************
	 * Below are the Actions methods for above locators
	 *****************************************/

	public WebElement getSmartStarterBronzePlusOption() {
		waitVisibility(smartStarterBronzePlusOption, driver);
		return smartStarterBronzePlusOption;
	}

	public WebElement getPrimeChoice() {
		waitVisibility(primeChoiceOption, driver);
		return primeChoiceOption;
	}

	public WebElement getTopCover() {
		waitVisibility(topCoverOption, driver);
		return topCoverOption;
	}

	// Action method to select "Starter Extras"
	public void selectStarterExtras() {
		waitVisibility(starterExtrasOption, driver);
		click(starterExtrasOption);
	}

	// Method to check if "Starter Extras" is enabled
	public boolean isStarterExtrasEnabled() {
		waitVisibility(starterExtrasOption, driver);
		return starterExtrasOption.isEnabled();
	}

	// Method to check if "Starter Extras" is disabled
	public boolean isStarterExtrasDisabled() {
		waitVisibility(starterExtrasOption, driver);
		return !starterExtrasOption.isEnabled();
	}

	// Action method to select click on cover type and then select "Extras Only"
	// from dropdown.

	// Action method to click "Edit button"
	public void clickEditButton() {
		waitVisibility(clickEditBtn, driver);
		click(clickEditBtn);
	}

	// Action method to select a specified coverage type e.g. Hospital & Extras,
	// Extra only, Hospital only from the dropdown
	public void selectCoverType(String coverType) {
		click(coverTypeDropdown); // Open dropdown
		Select coverDropdown = new Select(coverTypeDropdown);
		List<WebElement> options = coverDropdown.getOptions();
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(coverType)) { // Match cover type dynamically
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

	// Action method to select a specified coverage-for e.g. Family,Couple,Single
	// Parent,Family from the dropdown
	public void selectCoverFor(String coverFor) {
		click(coverageForDropdown); // Open dropdown
		Select coverForDrop = new Select(coverageForDropdown);
		List<WebElement> options = coverForDrop.getOptions();
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(coverFor)) { // Match cover type dynamically
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
		waitVisibility(inputPartnerDate, driver);
		sendKeys(inputPartnerDate, day);
	}

	// Action method to enter the month of birth
	public void enterPartnerMonthOfBirth(String month) {
		waitVisibility(inputPartnerMonth, driver);
		sendKeys(inputPartnerMonth, month);
	}

	// Action method to enter the year of birth
	public void enterPartnerYearOfBirth(String year) {
		waitVisibility(inputPartnerYear, driver);
		sendKeys(inputPartnerYear, year);
	}

	// Action method to click the "Click Update Details" button
	public void clickUpdateDetails() {
		waitVisibility(clickUpdateDetailsBtn, driver); // Wait for the element to be visible
		click(clickUpdateDetailsBtn);
	}

	// Action method to click on "Choose cover"
	public void clickChooseCovers() {
		waitVisibility(clickChooseCover, driver);
		click(clickChooseCover);
	}

	// Action method to select "Starter Extras"
	public void clickApplyNowButton() {
		waitVisibility(clickApplyNow, driver);
		click(clickApplyNow);
	}

	public void clickMemberStatusNoRadioButton() {
		// waitVisibility(memberStatusNoRadioBtn, driver);
		memberStatusNoRadioBtn.click();
		//return clickMemberStatusNoRadioButton();
	}

	// Click "Yes" for "Are you an Australian citizen or permanent resident?"
	public void clickCitizenshipYesRadioButton() {

		citizenshipYesRadioBtn.click();
	}

	public void clickHealthPractionerBtn() {
		click(clickHealthPractioner);

	}

	public void clickMedicalPractionerBtn() {
		click(clickMedicalPractioner);

	}

	public void selectRadioButtonByLabel(WebDriver driver, String label) {
		WebElement radioButtonToClick = null;

		switch (label) {
		case "Intern":
			radioButtonToClick = internRadioButton;
			break;
		case "Resident":
			radioButtonToClick = residentRadioButton;
			break;
		case "Registrar":
			radioButtonToClick = registrarRadioButton;
			break;
		case "General Practitioner":
			radioButtonToClick = generalPractitionerRadioButton;
			break;
		case "Specialist":
			radioButtonToClick = specialistRadioButton;
			break;
		case "Career Medical Officer":
			radioButtonToClick = careerMedicalOfficerRadioButton;
			break;
		case "Retired doctor":
			radioButtonToClick = retiredDoctorRadioButton;
			break;
		case "Other":
			radioButtonToClick = otherRadioButton;
			break;
		default:
			System.out.println("No radio button found for label: " + label);
			return;
		}

		if (radioButtonToClick != null) {
			radioButtonToClick.click();
			System.out.println("Clicked on radio button: " + label);
		}
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

	// Method to select Yes or No based on the parameter
	public void selectTransferFromAnotherFund(String response) {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		if ("Yes".equalsIgnoreCase(response)) {
			// wait.until(ExpectedConditions.elementToBeClickable(yesRadioButton));
			waitForElementToBeClickable(driver, yesRadioButton);
			yesRadioButton.click();
			System.out.println("Selected 'Yes' for transferring from another fund.");
		} else if ("No".equalsIgnoreCase(response)) {
			// wait.until(ExpectedConditions.elementToBeClickable(noRadioButton));
			waitForElementToBeClickable(driver, noRadioButton);
			noRadioButton.click();
			System.out.println("Selected 'No' for transferring from another fund.");
		} else {
			System.out.println("Invalid response: " + response + ". Please specify 'Yes' or 'No'.");
		}

	}

	public void alertAccept() {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try {
			// Wait for the alert or alert-like element to be visible
			// wait.until(ExpectedConditions.visibilityOf(acceptAlert));
			waitForVisibility(acceptAlert, driver);
			// Wait for it to be clickable
			waitForElementToBeClickable(driver, acceptAlert);
			// wait.until(ExpectedConditions.elementToBeClickable(acceptAlert));

			// Perform the click
			acceptAlert.click();
			System.out.println("Accepted the alert/modal.");
		} catch (TimeoutException e) {
			System.out.println("Alert/modal was not visible within the wait time.");
		} catch (Exception e) {
			System.out.println("An error occurred while interacting with the alert/modal: " + e.getMessage());
		}
	}

	public void nextButton() {
		waitVisibility(nextButton, driver);
		click(nextButton);
	}

	public void selectTitle(String titleForUser) throws InterruptedException {
		waitVisibilityElement(userTitle);
		click(userTitle); // Open dropdown
		// Wait for the dropdown options to be visible
		waitForAllOptionsVisibility(userTitle);
		Select titleDropdown = new Select(userTitle);
		List<WebElement> options = titleDropdown.getOptions();
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(titleForUser)) { // Match cover type dynamically
				titleDropdown.selectByVisibleText(titleForUser);
				log.info("Selected '" + titleForUser + "' from dropdown.");
				found = true;
				break;
			}
		}

		if (!found) {
			log.warn("Title type '" + titleForUser + "' not found in the dropdown.");
		}
	}

	// Method to enter random first, middle, and last names
	public void enterRandomFullName(WebDriver driver) {
		String fullName = generateRandomFullName(); // Generate the full name
		String[] nameParts = fullName.split(" "); // Split into first, middle, last
		waitVisibilityElement(firstNameInput);
		firstNameInput.clear();
		firstNameInput.sendKeys(nameParts[0]);

		waitVisibilityElement(middleNameInput);
		middleNameInput.clear();
		middleNameInput.sendKeys(nameParts[1]);

		waitVisibilityElement(lastNameInput);
		lastNameInput.clear();
		lastNameInput.sendKeys(nameParts[2]);

		System.out.println("Entered random full name: " + fullName);
	}

	// This method selects the radio button based on the label provided (Male,
	// Female, Other)
	public void selectGenderRadioButton(String gender) {
		switch (gender) {
		case "Male":
			maleRadioBtn.click();
			System.out.println("Selected Male radio button");
			break;
		case "Female":
			femaleRadioBtn.click();
			System.out.println("Selected Female radio button");
			break;
		case "Other":
			otherRadioBtn.click();
			System.out.println("Selected Other radio button");
			break;
		default:
			System.out.println("Invalid gender option provided");
		}
	}

	public String enterRandomEmail() {
		// Generate a random number
		int randomNumber = new Random().nextInt(1000); // Generate numbers between 0 and 999
		String randomEmail = "dhf.testing+" + randomNumber + "@gmail.com";

		// Enter the email into the input box
		emailInputBox.sendKeys(randomEmail);

		return randomEmail; // Return the email for validation in the test
	}

	// Generate and enter a random phone number
	public void enterRandomPhoneNumber() {
		// Generate a random number (4 digits) to append to the series
		String randomSuffix = String.valueOf((int) (Math.random() * 9000) + 1000);
		String generatedPhoneNumber = "049836" + randomSuffix;

		// Enter the generated phone number in the input box
		phoneInputBox.clear();
		phoneInputBox.sendKeys(generatedPhoneNumber);

		// Validate the generated phone number format
		Assert.assertTrue(generatedPhoneNumber.matches("049836\\d{4}"),
				"Phone number format validation failed. Generated Phone Number: " + generatedPhoneNumber);

	}

	public void typeAndSelectValue(String inputText, WebDriver driver) throws InterruptedException {
		// Clear the input box
		residentialAddressInputBox.clear();
		Actions actions = new Actions(driver);
		waitForElementToBeClickable(driver, residentialAddressInputBox);
		waitForVisibility(residentialAddressInputBox, driver);
		actions.sendKeys(residentialAddressInputBox, inputText).perform();
		Thread.sleep(1000);
		actions.sendKeys(Keys.ENTER).perform();
	}

	public void selectExistingAvantMemberOption(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			existingAvantMemberRadioButtonYes.click();
		} else if (option.equalsIgnoreCase("No")) {
			existingAvantMemberRadioButtonNo.click();
		} else {
			throw new IllegalArgumentException("Invalid option provided. Expected 'Yes' or 'No', but got: " + option);
		}
	}

	// Method to click the Your Details Continue Button
	public void clickYourDetailsContinueButton() {
		yourDetailsContinueButton.click();
	}

}
