package com.qa.airteam.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

	//.....Locator for continue button
	@FindBy(xpath = "//div[text()='Continue']") // div[text()='Continue']/../../..//button/..
	protected WebElement clickContinueButton;

	// to locate "Yes" and "No" radio buttons for the "Are you transferring from
	// another fund?"
	@FindBy(xpath = "//div[@name='isTransferringFromAnotherFund']//label[1]//span[contains(text(), 'Yes')]")
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

	// Locator for the dropdown to select the current health fund
	@FindBy(xpath = "//select[@name='currentHealthFund']/..")
	private WebElement currentHealthFund;

	@FindBy(xpath = "//input[@name='membershipNumber']")
	private WebElement membershipNumberInput;

	@FindBy(xpath = "//div[@name='partnerAndDependentsCovered']//span[contains(text(), 'Yes')]")
	private WebElement partnerAndDependentsYesCheckbox;

	@FindBy(xpath = "//div[@name='partnerAndDependentsCovered']//span[contains(text(), 'No')]")
	private WebElement partnerAndDependentsNoCheckbox;

	@FindBy(xpath = "//div[@name='lhcStatement']//span[2][contains(text(),'Yes')]")
	private WebElement lhcYesCheckbox;

	// No Checkbox
	@FindBy(xpath = "//div[@name='lhcStatement']//span[2][contains(text(),'No')]")
	private WebElement lhcNoCheckbox;

	// Age input box
	@FindBy(xpath = "//input[@name='firstHospitalCoverAge']")
	private WebElement ageInputBox;
	
	@FindBy(xpath = "//input[@name='mobileNumber']")
	protected WebElement partnerMobileInputBox;
	
	
	@FindBy(xpath = "//select[@name='relationship']")
	protected WebElement relationshipDropdown;
	/*******************************
	 * Actions Methods
	 *********************************/

	// Action method for check-box selection "No" : Are you a current member of
	// Doctors' Health Fund?
	public void clickMemberStatusNoRadioButton() {
		log.info("Attempting to select 'No' for member status.");
		waitForElementToBeClickable(driver, memberStatusNoRadioBtn);
		memberStatusNoRadioBtn.click();
		log.info("'No' radio button for member status selected successfully.");

	}

	// Action method for check-box selection "Yes: Are you an Australian citizen or
	// permanent resident?
	public void clickCitizenshipYesRadioButton() {
		log.info("Attempting to select 'Yes' for Australian citizenship status.");
		waitForElementToBeClickable(driver, citizenshipYesRadioBtn);
		citizenshipYesRadioBtn.click();
		log.info("'Yes' radio button for citizenship status selected successfully.");
	}

	// I clicked on medical practitioner button
	public void clickMedicalPractionerBtn() {
		log.info("Attempting to click the 'Medical Practitioner' button.");
		click(clickMedicalPractioner);
		log.info("'Medical Practitioner' button clicked successfully.");
	}

	// I selected any value from the available options: What kind of medical
	// practitioner are you?
	public void selectRadioButtonByLabel(WebDriver driver, String label) {
		log.info("Attempting to select the radio button for: {}", label);
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
			log.warn("No radio button found for label: {}", label);
			return;
		}

		if (radioButtonToClick != null) {
			radioButtonToClick.click();
			log.info("Radio button for '{}' selected successfully.", label);
		}
	}

	// Click on radio button "No" : Are you an existing Avant member?
	public void selectExistingAvantMemberOption(String option) {
		log.info("Attempting to select Avant member option: {}", option);
		if (option.equalsIgnoreCase("Yes")) {
			existingAvantMemberRadioButtonYes.click();
		} else if (option.equalsIgnoreCase("No")) {
			existingAvantMemberRadioButtonNo.click();
		} else {
			throw new IllegalArgumentException("Invalid option provided. Expected 'Yes' or 'No', but got: " + option);
		}
		log.info("Avant member option '{}' selected successfully.", option);
	}

	// Method to select Yes or No based on the parameter : Are you transferring from
	// another fund?

	public void selectTransferFromAnotherFund(String response) {
		log.info("Selecting option for transferring from another fund: {}", response);
		if ("Yes".equalsIgnoreCase(response)) {
			waitForElementToBeClickable(driver, yesRadioButton);
			yesRadioButton.click();
			log.info("'Yes' selected for transferring from another fund.");
		} else if ("No".equalsIgnoreCase(response)) {
			waitForVisibility(noRadioButton, driver);
			waitForElementToBeClickable(driver, noRadioButton);
			noRadioButton.click();
			log.info("'No' selected for transferring from another fund.");
		} else {
			log.warn("Invalid response: {}. Please specify 'Yes' or 'No'.", response);
		}
	}

	// Accept the modal
	public void alertAccept() {
		/*
		 * log.info("Attempting to accept alert/modal."); try {
		 * waitForVisibility(acceptAlert, driver); waitForElementToBeClickable(driver,
		 * acceptAlert); acceptAlert.click();
		 * log.info("Alert/modal accepted successfully."); } catch (TimeoutException e)
		 * { log.error("Alert/modal was not visible within the wait time.", e); } catch
		 * (Exception e) {
		 * log.error("An error occurred while interacting with the alert/modal: {}",
		 * e.getMessage()); }
		 */

		log.info("Attempting to accept alert/modal.");
		int retryCount = 3; // Number of retries
		int attempts = 0;

		while (attempts < retryCount) {
			try {
				waitForVisibility(acceptAlert, driver); // Wait for visibility of the element
				waitForElementToBeClickable(driver, acceptAlert); // Wait until the element is clickable
				acceptAlert.click(); // Attempt to click the alert/modal
				log.info("Alert/modal accepted successfully.");
				break; // Exit the loop if the click is successful
			} catch (ElementClickInterceptedException e) {
				attempts++;
				log.warn("Attempt {}: Alert/modal click intercepted. Retrying...", attempts, e);
				try {
					Thread.sleep(1000); // Wait before retrying
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
					log.error("Retry interrupted.", ie);
				}
			} catch (TimeoutException e) {
				log.error("Alert/modal was not visible within the wait time.", e);
				break; // Exit loop if visibility timeout occurs
			} catch (Exception e) {
				log.error("An error occurred while interacting with the alert/modal: {}", e.getMessage());
				break; // Exit loop for any other unexpected exceptions
			}
		}
	}

	// To click on Next button
	public void nextButton() {
		log.info("Attempting to click the 'Next' button.");
		waitVisibility(nextButton, driver);
		click(nextButton);
		log.info("'Next' button clicked successfully.");
	}

	// Action method to select Title as (Mr,,Mrs etc.)
	public void selectTitle(String titleForUser) throws InterruptedException {
		log.info("Selecting title from dropdown: {}", titleForUser);
		waitForVisibility(userTitle, driver);
		waitForElementToBeClickable(driver, userTitle);
		waitForAllOptionsVisibility(userTitle);
		Select titleDropdown = new Select(userTitle);
		List<WebElement> options = titleDropdown.getOptions();
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(titleForUser)) {
				titleDropdown.selectByVisibleText(titleForUser);
				log.info("Selected '{}' from dropdown.", titleForUser);
				found = true;
				break;
			}
		}

		if (!found) {
			log.warn("Title type '{}' not found in the dropdown.", titleForUser);
		}
	}

	// Action method to generate the random full user name
	public void enterRandomFullName(WebDriver driver) {
		String fullName = generateRandomFullName(); // Generate the full name
		String[] nameParts = fullName.split(" "); // Split into first, middle, last

		generatedFirstName = nameParts[0]; // Assigns generated FirstName e.g. "John" to the variable generatedFirstName

		generatedMiddleName = nameParts[1]; // Assigns generated MiddleName e.g. "Kumar" to the variable
		// generatedMiddleName
		generatedLastName = nameParts[2]; // Assigns generated LastName e.g. "Sharma" to the variable generatedLastName

		waitVisibilityElement(firstNameInput);
		firstNameInput.clear();
		firstNameInput.sendKeys(nameParts[0]);
		log.info("Entered First Name: {}", nameParts[0]);

		waitVisibilityElement(middleNameInput);
		middleNameInput.clear();
		middleNameInput.sendKeys(nameParts[1]);
		log.info("Entered Middle Name: {}", nameParts[1]);

		waitVisibilityElement(lastNameInput);
		lastNameInput.clear();
		lastNameInput.sendKeys(nameParts[2]);
		log.info("Entered Last Name: {}", nameParts[2]);
		log.info("Entered random full name: {}", fullName);

		// System.out.println("Entered random full name: " + fullName);
	}

	// This method selects the radio button based on the label provided (Male,
	// Female, Other)
	public void selectGenderRadioButton(String gender) {
		switch (gender) {
		case "Male":
			maleRadioBtn.click();
			log.info("Selected Male radio button");
			break;
		case "Female":
			femaleRadioBtn.click();
			log.info("Selected Female radio button");
			break;
		case "Other":
			otherRadioBtn.click();
			log.info("Selected Other radio button");
			break;
		default:
			log.error("Invalid gender option provided: {}", gender);
		}
	}

	// Action method to generate random email
	public String enterRandomEmail() {
		// Generate a random number
		int randomNumber = new Random().nextInt(1000); // Generate numbers between 0 and 999
		String randomEmail = "dhf.testing+" + randomNumber + "@gmail.com";

		// Assign the generated email to the static variable generatedEmail
		generatedEmail = randomEmail;

		// Enter the email into the input box
		emailInputBox.sendKeys(randomEmail);
		log.info("Entered random email: {}", randomEmail);

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
		log.info("Entered random phone number: {}", generatedPhoneNumber);

		// Validate the generated phone number format
		boolean isValid = generatedPhoneNumber.matches("049836\\d{4}");
		Assert.assertTrue(isValid,
				"Phone number format validation failed. Generated Phone Number: " + generatedPhoneNumber);
		log.info("Phone number format validation passed: {}", generatedPhoneNumber);
	}

	// Action method for address selection
	public void typeAndSelectValue(String inputText, WebDriver driver) throws InterruptedException {
		residentialAddressInputBox.clear();
		Actions actions = new Actions(driver);

		waitForElementToBeClickable(driver, residentialAddressInputBox);
		waitForVisibility(residentialAddressInputBox, driver);
		Thread.sleep(1000);
		actions.sendKeys(residentialAddressInputBox, inputText).perform();
		log.info("Entered address input: {}", inputText);
		Thread.sleep(1000);
		actions.sendKeys(Keys.ENTER).perform();
		log.info("Selected address input: {}", inputText);
	}

	// Action method to click "Continue radio button"
	public WebElement clickContinueButton() throws InterruptedException {
		waitForVisibility(clickContinueButton, driver);
		waitForElementToBeClickable(driver, clickContinueButton);
		try {
			click(clickContinueButton);
			log.info("Clicked on Continue button");
		} catch (ElementClickInterceptedException e) {
			log.error("Element click intercepted. Consider adding a retry mechanism.");
			throw e; // Re-throw to handle in the calling method
		}

		return clickContinueButton;
	}

	// Method to click the dropdown and select a value
	public void selectCurrentHealthFund(String healthFundName, WebDriver driver) throws InterruptedException {
		scrollToElement(currentHealthFund, driver);
		currentHealthFund.click(); // Click to open the dropdown

		List<WebElement> options = currentHealthFund.findElements(By.tagName("option")); // Locate all options
		for (WebElement option : options) {
			if (option.getText().equals(healthFundName)) {
				option.click(); // Click the matching option

				// Send Enter key to confirm the selection
				Actions actions = new Actions(driver);
				actions.click().build().perform();
				// actions.sendKeys(Keys.ENTER).perform();
				break;
			}
		}
	}

	// Method to enter a membership number provided from the feature file
	public void enterMembershipNumber(String membershipNumber, WebDriver driver) {
		membershipNumberInput.clear();
		membershipNumberInput.sendKeys(membershipNumber);

	}

	public void selectPartnerAndDependentsCovered(String option, WebDriver driver) {

		try {
			if (option.equalsIgnoreCase("Yes")) {
				scrollToElement(partnerAndDependentsYesCheckbox, driver);
				partnerAndDependentsYesCheckbox.click();
				System.out.println("Clicked on 'Yes' checkbox for Partner and Dependents Covered.");
			} else if (option.equalsIgnoreCase("No")) {
				scrollToElement(partnerAndDependentsNoCheckbox, driver);
				partnerAndDependentsNoCheckbox.click();
				System.out.println("Clicked on 'No' checkbox for Partner and Dependents Covered.");
			} else {
				throw new IllegalArgumentException("Invalid option: " + option + ". Use 'Yes' or 'No'.");
			}
		} catch (Exception e) {
			System.out.println("Error while selecting Partner and Dependents Covered option: " + e.getMessage());
		}
	}

	// Action method to select checkbox based on input
	public void selectLhcCheckbox(String option, WebDriver driver) {
		try {
			if (option.equalsIgnoreCase("Yes")) {
				scrollToElement(lhcYesCheckbox, driver);
				lhcYesCheckbox.click();
				System.out.println("Clicked on 'Yes' checkbox for LHC.");
			} else if (option.equalsIgnoreCase("No")) {
				scrollToElement(lhcNoCheckbox, driver);
				lhcNoCheckbox.click();
				System.out.println("Clicked on 'No' checkbox for LHC.");
			} else {
				throw new IllegalArgumentException("Invalid option: " + option + ". Use 'Yes' or 'No'.");
			}
		} catch (Exception e) {
			System.out.println("Error while selecting LHC Yes or No option: " + e.getMessage());
		}
	}

	// Action method to provide age value
	public void enterAgeValue(int age) {
		ageInputBox.clear(); // Clear any existing value
		ageInputBox.sendKeys(String.valueOf(age)); // Input the age
	}
	
	// Action method to generate and enter a random phone number
		public void enterPartnersRandomMobileNumber() {
			// Generate a random number (4 digits) to append to the series
			String randomSuffix = String.valueOf((int) (Math.random() * 9000) + 1000);
			String generatedMobileNumber = "049836" + randomSuffix;

			// Enter the generated phone number in the input box
			partnerMobileInputBox.clear();
			partnerMobileInputBox.sendKeys(generatedMobileNumber);
			log.info("Entered random mobile number: {}", generatedMobileNumber);

			// Validate the generated phone number format
			boolean isValid = generatedMobileNumber.matches("049836\\d{4}");
			Assert.assertTrue(isValid,
					"Phone number format validation failed. Generated Phone Number: " + generatedMobileNumber);
			log.info("Phone number format validation passed: {}", generatedMobileNumber);
		}
		
		// Method to click the dropdown and select a value
		public void selectRelationship(String relationship, WebDriver driver) throws InterruptedException {
			scrollToElement(relationshipDropdown, driver);
			relationshipDropdown.click(); // Click to open the dropdown

			List<WebElement> options = relationshipDropdown.findElements(By.tagName("option")); // Locate all options
			 for (WebElement option : options) {
			        if (option.getText().trim().equalsIgnoreCase(relationship)) { // Compare the option text with the passed value
			            option.click(); // Click the matching option
			            Thread.sleep(500); // Small delay to ensure selection
			            break; // Exit loop after finding and selecting the desired option
			        }
			        // Press the Escape key to close the dropdown
			        relationshipDropdown.sendKeys(Keys.ESCAPE);
					/*
					 * // Send Enter key to confirm the selection Actions actions = new
					 * Actions(driver); //actions.click().build().perform();
					 * actions.sendKeys(Keys.ENTER).perform(); break;
					 */
				}
			}
		
	
	
}
