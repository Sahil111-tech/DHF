package com.qa.airteam.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.BaseTest;

public class EligibilityAndYourDetailsPage extends BaseTest {
	
	  // Public static variables for Firstname, Middle name, and Lastname
    public static String generatedFirstName;
    public static String generatedMiddleName;
    public static String generatedLastName;
    public static String generatedEmail;

	// constructor for initialization page factory and drivers
	public EligibilityAndYourDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/***** Covering here EligibilityAndYourDetailsPage *****/
	// "No" radio button for "Are you a current member of Doctors' Health Fund?"
	@FindBy(xpath = "//div[@name='existingPolicyHolder']//label[2]/span[text()='No']")
	WebElement memberStatusNoRadioBtn;

	// "Yes" radio button for "Are you an Australian citizen or permanent resident?"
	@FindBy(xpath = "//div[@name='australianCitizen']//label[1]/span[text()='Yes']")
	protected WebElement citizenshipYesRadioBtn;

	// Locator for click medical practioner button
	@FindBy(xpath = "//div[text()='Medical practitioner']")
	protected WebElement clickMedicalPractioner;

	// Locator to select intern radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Intern']")
	protected WebElement internRadioButton;

	// Locator to select resident radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Resident']")
	protected WebElement residentRadioButton;

	// Locator to select registrar radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Registrar']")
	protected WebElement registrarRadioButton;

	// Locator to select General Practitioner radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='General Practitioner']")
	protected WebElement generalPractitionerRadioButton;

	// Locator to select Specialist radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Specialist']")
	protected WebElement specialistRadioButton;

	// Locator to select Career Medical Officer Radio Button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Career Medical Officer']")
	protected WebElement careerMedicalOfficerRadioButton;

	// Locator to select retired doctor radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Retired doctor']")
	protected WebElement retiredDoctorRadioButton;

	// Locator to select other radio button
	@FindBy(xpath = "//div[text()='What kind of medical practitioner are you?']/..//span[text()='Other']")
	protected WebElement otherRadioButton;

	// XPath for the Yes radio buttons for "Are you an existing Avant member?"
	@FindBy(xpath = "//div[@name='existingAvantMember']//div/label[1][span[contains(text(),'Yes')]]")
	private WebElement existingAvantMemberRadioButtonYes;

	// XPath for the No radio buttons for "Are you an existing Avant member?"
	@FindBy(xpath = "//div[@name='existingAvantMember']//div/label[2][span[contains(text(),'No')]]")
	private WebElement existingAvantMemberRadioButtonNo;

	// Locator for continue button
	@FindBy(xpath = "//div[text()='Continue']/../../..//button/..") // "//div[@class='css-159nnwp']//div[@class='css-1p15x7ze18zzrqo1']"
	protected WebElement clickContinueButton;

	// to locate "Yes" and "No" radio buttons for the "Are you transferring from
	// another fund?"
	@FindBy(xpath = "//div[@name='isTransferringFromAnotherFund']//label//span[text()='Yes']")
	private WebElement yesRadioButton;

	@FindBy(xpath = "//div[@name='isTransferringFromAnotherFund']//label[2]//span[text()='No']")
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

	/*******************************
	 * Actions Methods
	 *********************************/

	// Action method for check-box selection "No" : Are you a current member of
	// Doctors' Health Fund?
	public void clickMemberStatusNoRadioButton() {
		waitForElementToBeClickable(driver, memberStatusNoRadioBtn);
		memberStatusNoRadioBtn.click();

	}

	// Action method for check-box selection "Yes: Are you an Australian citizen or
	// permanent resident?
	public void clickCitizenshipYesRadioButton() {
		waitForElementToBeClickable(driver, citizenshipYesRadioBtn);
		citizenshipYesRadioBtn.click();
	}

	// I clicked on medical practitioner button
	public void clickMedicalPractionerBtn() {
		click(clickMedicalPractioner);

	}

	// I selected any value from the available options: What kind of medical
	// practitioner are you?
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

	// Click on radio button "No" : Are you an existing Avant member?
	public void selectExistingAvantMemberOption(String option) {
		if (option.equalsIgnoreCase("Yes")) {
			existingAvantMemberRadioButtonYes.click();
		} else if (option.equalsIgnoreCase("No")) {
			existingAvantMemberRadioButtonNo.click();
		} else {
			throw new IllegalArgumentException("Invalid option provided. Expected 'Yes' or 'No', but got: " + option);
		}
	}

	// Method to select Yes or No based on the parameter : Are you transferring from
	// another fund?

	public void selectTransferFromAnotherFund(String response) {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		if ("Yes".equalsIgnoreCase(response)) {
			// wait.until(ExpectedConditions.elementToBeClickable(yesRadioButton));
			waitForElementToBeClickable(driver, yesRadioButton);
			yesRadioButton.click();
			System.out.println("Selected 'Yes' for transferring from another fund.");
		} else if ("No".equalsIgnoreCase(response)) {
			// wait.until(ExpectedConditions.elementToBeClickable(noRadioButton));
			waitForVisibility(noRadioButton, driver);
			waitForElementToBeClickable(driver, noRadioButton);
			noRadioButton.click();
			System.out.println("Selected 'No' for transferring from another fund.");
		} else {
			System.out.println("Invalid response: " + response + ". Please specify 'Yes' or 'No'.");
		}

	}

	// Accept the modal
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

    //To click on Next button
	public void nextButton() {
		waitVisibility(nextButton, driver);
		click(nextButton);
	}

	// Action method to select Title as (Mr,,Mrs etc.)
	public void selectTitle(String titleForUser) throws InterruptedException {
		waitForVisibility(userTitle, driver);
		waitForElementToBeClickable(driver, userTitle);
		// click(userTitle); // Open dropdown
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

	// Action method to generate the random full user name
	public void enterRandomFullName(WebDriver driver) {
		String fullName = generateRandomFullName(); // Generate the full name
		String[] nameParts = fullName.split(" "); // Split into first, middle, last
		
		
        generatedFirstName = nameParts[0]; // Assigns generated FirstName e.g. "John" to the variable generatedFirstName
        generatedMiddleName = nameParts[1]; // Assigns generated MiddleName e.g. "Kumar" to the variable generatedMiddleName
        generatedLastName = nameParts[2];  // Assigns generated LastName e.g. "Sharma" to the variable generatedLastName
        
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

	// Action method to generate random email
	public String enterRandomEmail() {
		// Generate a random number
		int randomNumber = new Random().nextInt(1000); // Generate numbers between 0 and 999
		String randomEmail = "dhf.testing+" + randomNumber + "@gmail.com";
		
		// Assign the generated email to the static variable  generatedEmail 
        generatedEmail = randomEmail;  

		// Enter the email into the input box
		emailInputBox.sendKeys(randomEmail);

		return randomEmail; // Return the email for validation in the test
	}

	// Action method to generate and enter a random phone number
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

	// Action method for address selection
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
