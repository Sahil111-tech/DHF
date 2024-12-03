package com.qa.sugarcrm.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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

public class LeadsAndPersonalDetailsPage extends BaseTest {
	protected WebDriver driver;
	// Public static variables for first, middle, and last names
	public static String firstName;
	public static String middleName;
	public static String lastName;
	public static String generatedEmail;
	public static final Random random = new Random();
	public static String generatedPhoneNumber;
	public static String fullName;
	public static String Salutation;

	// constructor for initialization page factory and drivers
	public LeadsAndPersonalDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locate the leads button
	@FindBy(xpath = "//div[@id='Leads_sidebar-nav-item']/a")
	private WebElement leadsButton;

	// Locate the create button
	@FindBy(xpath = "//a[@name='create_button']")
	private WebElement createButton;

	// XPath to click on the "Salutation" dropdown to open options
	@FindBy(xpath = "//input[@class='select2']/ancestor::span[@data-fieldname='salutation']")
	private WebElement salutationDropdownButton;

	// XPath to get all the options in the "Salutation" dropdown
	@FindBy(xpath = "(//span[@data-name='salutation']//following::ul[starts-with(@id, 'select2-results-')])[46]//li")
	private List<WebElement> salutationDropdownOptions;

	// Locate first name field
	@FindBy(xpath = "//input[@name='first_name']")
	private WebElement firstNameField;

	// Locate middle name field
	@FindBy(xpath = "(//input[@name='middle_name_c' and @aria-label='middle name' ])[1]")
	private WebElement middleNameField;

	// Locate last name field
	@FindBy(xpath = "(//input[@name='last_name' and @aria-label='Last Name' ])[1]")
	private WebElement lastNameField;

	// xpath to click on "Brand" dropdown
	@FindBy(xpath = "//span[@data-fieldname='brand_select_c']/..")
	private WebElement brandDropdownButton;

	// xpath to get all the li's from the "Brand" dropdown
	@FindBy(xpath = "//div[@id='select2-drop']//ul[@class='select2-results']//li")
	private List<WebElement> brandDropdownOptions;

	// xpath to click on "Lead Source" dropdown
	@FindBy(xpath = "//span[@data-fieldname='lead_source']/..")
	private WebElement leadSourceDropdownButton;

	// xpath to get all the li's from the "Lead Source" dropdown
	@FindBy(xpath = "(//span[text()='Lead Source']//following::ul[starts-with(@id, 'select2-results-')])[45]//li") //// div[@id='select2-drop']//ul[@id='select2-results-10']//li
	private List<WebElement> leadSourceDropdownOptions;

	// xpath to click on "Lead Type" dropdown
	@FindBy(xpath = "//span[@data-fieldname='lead_type_c']/..")
	private WebElement leadTypeDropdownButton;

	// xpath to get all the li's from the "Lead Type" dropdown
	@FindBy(xpath = "//div[@id='select2-drop']//ul[@id='select2-results-16']//li")
	private List<WebElement> leadTypeDropdownOptions;

	// locate checkbox for prCitizenCheckbox
	@FindBy(xpath = "//input[@aria-label='PR /  Citizen']")
	private WebElement prCitizenCheckbox;

	// xpath to click on "Eligibility id" dropdown
	@FindBy(xpath = "//span[@data-fieldname='elidgability_c']/..")
	private WebElement eligibilityIdDropdownButton;

	// xpath to get all the li's from the "Eligibility id" dropdown
	@FindBy(xpath = "//div[@id='select2-drop']//ul[starts-with(@id,'select2-results-')]//li")
	private List<WebElement> eligibilityIdDropdownOptions;

	// XPath for the Birth Date input field
	@FindBy(xpath = "//input[@name='birthdate']")
	private WebElement birthDateInput;

	// Locator for Phone Number field
	@FindBy(xpath = "//input[@name='phone_mobile']")
	protected WebElement phoneInputBox;

	// XPath for the email input field
	@FindBy(xpath = "//input[@type='text' and @class='newEmail input-append' and @placeholder='Add email']")
	private WebElement emailInputBox;

	// xpath for the input box
	@FindBy(xpath = "//textarea[@name='primary_address_street']")
	private WebElement primaryAddressInputBox;
	@FindBy(xpath = "//span[@class='h-full w-15']")
	private WebElement hamburgerMenu;

	// xpath to click on "Agent Lead Score" dropdown
	@FindBy(xpath = "//span[@data-fieldname='agent_lead_score_c']/..")
	private WebElement agentLeadScoreDropdownButton;

	// xpath to get all the li's from the "Agent Lead Score" dropdown
	@FindBy(xpath = "//div[@id='select2-drop']//ul[starts-with(@id,'select2-results')]//li")
	private List<WebElement> agentLeadScoreDropdownOptions;

	// xpath to click on "Eligibility Details Dropdown Button" dropdown
	@FindBy(xpath = "//span[@data-fieldname='eligilibilty_detail_c']")
	private WebElement eligibilityDetailsDropdownButton;

	// Locate the dropdown to open the Gender options
	@FindBy(xpath = "//span[@data-fieldname='gender_c']")
	private WebElement genderDropdown;

	// xpath to get all the li's from the "Eligibility Details" dropdown
	@FindBy(xpath = "(//span[text()='Eligibility Detail']//following::ul[starts-with(@id, 'select2-results-')])[40]//li")
	private List<WebElement> eligibilityDetailsDropdownOptions;

	// Locate how did you hear about us dropdown button
	@FindBy(xpath = "//span[@data-fieldname='hdyh_c']")
	private WebElement howDidYouHearDropdownButton;

	// Locate save button
	@FindBy(xpath = "//a[@name='save_button']")
	private WebElement saveButton;

	// Eligibility details button
	@FindBy(xpath = "//span[@data-fieldname='eligilibilty_detail_c']")
	private WebElement eligibilityDetailsButton;

	// Locate the element where I have to send the fullname stored in another
	// variable
	@FindBy(xpath = "//input[@placeholder='Search by first name, last name...']")
	private WebElement searchUserInputBox;

	// Locate the element where I have to send the fullname stored in another
	// variable
	@FindBy(xpath = "(//a[contains(@class, 'ellipsis_inline') and @data-module='Leads'])[1]")
	private WebElement clickSearchedUser;

	@FindBy(xpath = "//div[@class='btn-toolbar pull-right']//a[@track='click:duplicate_button']")
	private WebElement clickIgnoreDuplicateAndSaveButton;

	@FindBy(xpath = "//strong[starts-with(text(),'Warning:')]")
	private WebElement warningPopup;

	@FindBy(xpath = "//a[text()='Confirm']")
	private WebElement clickConfirmButton;

	@FindBy(xpath = "//div[@class='row-fluid flex justify-end']//a[@data-action='confirm']")
	private WebElement duplicateLeadPopupConfirmButton;

	/*** Actions Method ****/

	// Action method to click on the Leads button
	public void clickLeadsButton() {
		try {
			// Attempt to click the button
			waitForVisibility(leadsButton, driver);
			leadsButton.click();
			log.info("Clicked successfully on the Leads button.");
		} catch (StaleElementReferenceException e) {
			log.warn("Encountered a StaleElementReferenceException. Reinitializing elements.");
			// Reinitialize the PageFactory elements to refresh all locators
			PageFactory.initElements(driver, this);
			waitForVisibility(leadsButton, driver);
			leadsButton.click();
			log.info("Clicked successfully on the Leads button after reinitializing elements.");
		}
	}

	// Action method to click on the Create button
	public void clickCreateButton() {
		waitForVisibility(createButton, driver);
		createButton.click();
		log.info("Clicked succesfully on the Create button.");
	}

	public void selectSalutation(String salutation) {
		// Click on the dropdown button to open options
		salutationDropdownButton.click();

		// Wait for options to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(salutationDropdownOptions));

		// Iterate through the options and select the desired one
		for (WebElement option : salutationDropdownOptions) {
			if (option.getText().equalsIgnoreCase(salutation)) {
				option.click();
				Salutation = salutation; // Store the selected salutation
				System.out.println("Selected salutation: " + Salutation);
				break;
			}
		}
	}

	// Action method to generate and enter a random full name
	public void enterRandomFullName(WebDriver driver) {
		fullName = generateRandomFullName();
		// Generate the full name
		String[] nameParts = fullName.split(" "); // Split into first, middle, last

		// Enter the First Name waitVisibilityElement(firstNameField);
		firstNameField.clear();
		firstNameField.sendKeys(nameParts[0]);

		// Enter the Middle Name waitVisibilityElement(middleNameField);
		middleNameField.clear();
		middleNameField.sendKeys(nameParts[1]);

		// Enter the Last Name waitVisibilityElement(lastNameField);
		lastNameField.clear();
		lastNameField.sendKeys(nameParts[2]);

		// Logging the generated full name
		System.out.println("Entered random full name: " + fullName);
	}

	// Helper method to generate a random full name
	public String generateRandomFullName() { // Static arrays for names
		String[] firstNames = { "Ethan", "Emma", "Mason", "Olivia", "Lucas", "Mia", "Aiden", "Amelia", "Jackson",
				"Aria" };
		String[] middleNames = { "Grace", "Michael", "Alexander", "Riley", "Thomas", "Zoe", "Joseph", "Sophia", "Henry",
				"Scarlett" };
		String[] lastNames = { "Anderson", "Clark", "Harris", "Lewis", "Martin", "Young", "Allen", "King", "Scott",
				"Adams" };

		// Set to ensure uniqueness
		Set<String> generatedNames = new HashSet<>();

		// Random generator
		Random random = new Random();

		String fullName;

		do { // Pick random names
			String firstName = firstNames[random.nextInt(firstNames.length)];
			String middleName = middleNames[random.nextInt(middleNames.length)];
			String lastName = lastNames[random.nextInt(lastNames.length)];

			// Combine to form the full name
			fullName = firstName + " " + middleName + " " + lastName;

		} while (generatedNames.contains(fullName)); // Ensure uniqueness

		// Add to the set to track it
		generatedNames.add(fullName);

		return fullName;
	}

	/*
	 * // Method to enter names public void enterNames(String firstName, String
	 * middleName, String lastName) { // Enter first name firstNameField.clear();
	 * firstNameField.sendKeys(firstName); System.out.println("First name entered: "
	 * + firstName);
	 * 
	 * // Enter middle name middleNameField.clear();
	 * middleNameField.sendKeys(middleName);
	 * System.out.println("Middle name entered: " + middleName);
	 * 
	 * // Enter last name lastNameField.clear(); lastNameField.sendKeys(lastName);
	 * System.out.println("Last name entered: " + lastName); }
	 */

	// Action method to select a value from the "Brand" dropdown
	public void selectValueFromBrandDropdown(String valueToSelect) {
		// Step 1: Click the dropdown button
		waitVisibilityElement(brandDropdownButton);
		brandDropdownButton.click();
		log.info("Dropdown opened successfully.");

		// Step 2: Wait for dropdown options to load
		waitVisibilityElement(brandDropdownOptions.get(0));

		// Step 3: Find and select the desired value
		boolean valueFound = false;
		for (WebElement option : brandDropdownOptions) {
			String optionText = option.getText().trim();
			if (optionText.equalsIgnoreCase(valueToSelect)) {
				option.click();
				valueFound = true;
				System.out.println("Selected value: " + optionText);
				break;
			}
		}

		// Step 4: Throw an exception if the value was not found
		if (!valueFound) {
			throw new RuntimeException("Value '" + valueToSelect + "' not found in the dropdown.");
		}
	}

	public void selectValueFromLeadSourceDropdown(String valueToSelect) {
		// Step 1: Click the "Lead Source" dropdown button
		waitVisibilityElement(leadSourceDropdownButton);
		leadSourceDropdownButton.click();
		System.out.println("Lead Source dropdown opened successfully.");

		// Step 2: Wait for the dropdown options to be visible
		waitVisibilityElement(leadSourceDropdownOptions.get(0));

		// Step 3: Iterate through the dropdown options to find and select the matching
		// value
		boolean valueFound = false;
		for (WebElement option : leadSourceDropdownOptions) {
			String optionText = option.getText().trim();
			if (optionText.equalsIgnoreCase(valueToSelect)) {
				option.click(); // Select the matching value
				valueFound = true;
				System.out.println("Selected Lead Source value: " + optionText);
				break;
			}
		}

		// Step 4: Handle case when the value is not found
		if (!valueFound) {
			throw new RuntimeException("Value '" + valueToSelect + "' not found in the Lead Source dropdown.");
		}
	}

	// Action method to select the "PR / Citizen" checkbox
	public void selectPRCitizenCheckbox() {
		try {
			// Check if the checkbox is already selected
			if (!prCitizenCheckbox.isSelected()) {
				// If not selected, click to select it
				prCitizenCheckbox.click();
				log.info("Successfully selected the PR / Citizen checkbox.");
			} else {
				log.info("PR / Citizen checkbox is already selected.");
			}
		} catch (StaleElementReferenceException e) {
			log.warn("Encountered a StaleElementReferenceException. Reinitializing elements.");
			// Reinitialize the PageFactory elements to refresh all locators
			PageFactory.initElements(driver, this);
			// Retry the checkbox selection
			if (!prCitizenCheckbox.isSelected()) {
				prCitizenCheckbox.click();
				log.info("Successfully selected the PR / Citizen checkbox after reinitializing elements.");
			}
		}
	}

	public void selectValueFromEligibilityIdDropdown(String valueToSelect) {
		// Step 1: Click the "Eligibility ID" dropdown button
		waitVisibilityElement(eligibilityIdDropdownButton);
		eligibilityIdDropdownButton.click();
		System.out.println("Eligibility ID dropdown opened successfully.");

		// Step 2: Wait for the dropdown options to be visible
		waitVisibilityElement(eligibilityIdDropdownOptions.get(0));

		// Step 3: Iterate through the dropdown options to find and select the matching
		// value
		boolean valueFound = false;
		for (WebElement option : eligibilityIdDropdownOptions) {
			String optionText = option.getText().trim();
			if (optionText.equalsIgnoreCase(valueToSelect)) {
				option.click(); // Select the matching value
				valueFound = true;
				System.out.println("Selected Eligibility ID value: " + optionText);
				break;
			}
		}

		// Step 4: Handle case when the value is not found
		if (!valueFound) {
			throw new RuntimeException("Value '" + valueToSelect + "' not found in the Eligibility ID dropdown.");
		}
	}

	public void selectValueFromLeadTypeDropdown(int stepsToTraverse) {
		/*
		 * // Step 1: Click the "Lead Type" dropdown button
		 * waitVisibilityElement(leadTypeDropdownButton);
		 * leadTypeDropdownButton.click();
		 * System.out.println("Lead Type dropdown opened successfully.");
		 * 
		 * // Step 2: Wait for the dropdown options to be visible
		 * waitVisibilityElement(leadTypeDropdownOptions.get(0));
		 * 
		 * // Step 3: Iterate through the dropdown options to find and select the
		 * matching // value boolean valueFound = false; for (WebElement option :
		 * leadTypeDropdownOptions) { String optionText = option.getText().trim(); if
		 * (optionText.equalsIgnoreCase(valueToSelect)) { option.click(); // Select the
		 * matching value valueFound = true;
		 * System.out.println("Selected Lead Type value: " + optionText); break; } }
		 * 
		 * // Step 4: Handle case when the value is not found if (!valueFound) { throw
		 * new RuntimeException("Value '" + valueToSelect +
		 * "' not found in the Lead Type dropdown."); } }
		 */

		// Wait for the input box to be visible
		waitVisibilityElement(leadTypeDropdownButton);
		leadTypeDropdownButton.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Action method to enter the date of birth
	public void enterBirthDate(String dateOfBirth) {
		// Validate the date format (dd/MM/yyyy) and ensure it's not a future date or
		// invalid calendar date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
			LocalDate today = LocalDate.now();

			// Check if the date is in the future
			if (birthDate.isAfter(today)) {
				throw new IllegalArgumentException("Date of birth cannot be in the future.");
			}

			// Check if the date is valid in the calendar
			int day = birthDate.getDayOfMonth();
			int month = birthDate.getMonthValue();
			LocalDate firstDayOfMonth = birthDate.withDayOfMonth(1);
			LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());

			if (day < 1 || day > lastDayOfMonth.getDayOfMonth()) {
				throw new IllegalArgumentException("Invalid date as per the calendar rules.");
			}

			// Enter the validated date into the input field
			waitVisibilityElement(birthDateInput);
			birthDateInput.clear();
			birthDateInput.sendKeys(dateOfBirth);

			// Press Enter to close the calendar
			birthDateInput.sendKeys(Keys.ENTER);

			System.out.println("Entered birth date: " + dateOfBirth);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy format.");
		}

	}

	// Action method to generate and enter a random phone number
	public void enterRandomPhoneNumber() {
		// Generate a random number (4 digits) to append to the series
		String randomSuffix = String.valueOf((int) (Math.random() * 9000) + 1000);
		generatedPhoneNumber = "049836" + randomSuffix; // Set the generated number

		// Enter the generated phone number in the input box
		phoneInputBox.clear();
		phoneInputBox.sendKeys(generatedPhoneNumber);

		// Validate the generated phone number format
		Assert.assertTrue(generatedPhoneNumber.matches("049836\\d{4}"),
				"Phone number format validation failed. Generated Phone Number: " + generatedPhoneNumber);

		log.info("Generated and entered phone number: " + generatedPhoneNumber);
	}

	// Action method to generate random email
	public String enterRandomEmail() {
		// Generate a random number
		int randomNumber = random.nextInt(1000); // Generate numbers between 0 and 999
		String randomEmail = "dhf.testing+" + randomNumber + "@gmail.com";

		// Assign the generated email to the static variable generatedEmail
		generatedEmail = randomEmail;
		// Enter the email into the input box
		emailInputBox.sendKeys(randomEmail);

		return randomEmail; // Return the email for validation in the test
	}

	public void handleDynamicDropdownWithKeyboard(String inputText, int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(primaryAddressInputBox);
		primaryAddressInputBox.clear();
		primaryAddressInputBox.sendKeys(inputText);
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}

		actions.click().build().perform();
		hamburgerMenu.click();

		// System.out.println("Selected the " + stepsToTraverse + "nd option for input:
		// " + inputText);
	}

	public void selectAgentLeadScoreOption(String valueToSelect) {
		// Step 1: Click the "Lead Type" dropdown button
		waitVisibilityElement(agentLeadScoreDropdownButton);
		agentLeadScoreDropdownButton.click();
		System.out.println("Agent Lead Score dropdown opened successfully.");

		boolean valueFound = false;
		for (WebElement option : agentLeadScoreDropdownOptions) {
			String optionText = option.getText().trim();
			if (optionText.equalsIgnoreCase(valueToSelect)) {
				option.click(); // Select the matching value
				valueFound = true;
				System.out.println("Selected Lead Type value: " + optionText);
				break;
			}
		}

		// Step 4: Handle case when the value is not found
		if (!valueFound) {
			throw new RuntimeException("Value '" + valueToSelect + "' not found in the Agent Lead Score dropdown.");
		}
	}

	public void selectEligibilityDetailsOption(String valueToSelect) {
		// Step 1: Click the "Lead Type" dropdown button
		waitVisibilityElement(eligibilityDetailsDropdownButton);
		eligibilityDetailsDropdownButton.click();
		System.out.println("Eligibility details dropdown opened successfully.");
		boolean valueFound = false;
		for (WebElement option : eligibilityDetailsDropdownOptions) {
			String optionText = option.getText().trim();
			if (optionText.equalsIgnoreCase(valueToSelect)) {
				option.click(); // Select the matching value
				valueFound = true;
				System.out.println("Selected Lead Type value: " + optionText);
				break;
			}
		}

		// Step 4: Handle case when the value is not found
		if (!valueFound) {
			throw new RuntimeException("Value '" + valueToSelect + "' not found in the eligibility details dropdown.");
		}
	}

	/**
	 * Action method to handle the dropdown and select the "No" option using
	 * keyboard scrolling.
	 */
	public void selectOptionFromGenderDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(genderDropdown);
		genderDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	public void selectDHFSalesSpecialist() {
		// Locate the dropdown and click on it
		WebElement dropdown = driver.findElement(By.xpath("//span[@data-fieldname='hdyh_c']"));
		dropdown.click();

		// Add a wait to ensure the dropdown options are visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//span[text()='How did you hear about us?']/following::span[text()='DHF Sales Specialist']")));

		// Click on the desired option
		option.click();

		// Log the action for confirmation
		log.info("Selected 'DHF Sales Specialist' from the dropdown.");
	}

	public void clickSaveButton() {
		int attempts = 0; // Retry up to 3 times

		while (attempts < 3) {
			try {
				// Wait for the Save button to be visible and clickable
				waitForVisibility(saveButton, driver);
				waitForElementToBeClickable(driver, saveButton);
				saveButton.click();
				System.out.println("Save button clicked successfully.");

				// Temporarily set implicit wait to zero to handle specific elements
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

				// Create WebDriverWait to use for subsequent element interactions
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Adjust timeout if needed

				// Handle the "Confirm" button if the popup appears
				try {
					wait.until(ExpectedConditions.elementToBeClickable(clickConfirmButton));
					clickConfirmButton.click();
					System.out.println("'Confirm' button clicked directly.");
				} catch (TimeoutException e) {
					System.out.println("Confirm button not present. Moving to the next step.");
				}

				// Handle the "Ignore Duplicate and Save" button if it appears directly
				try {
					wait.until(ExpectedConditions.elementToBeClickable(clickIgnoreDuplicateAndSaveButton));
					clickIgnoreDuplicateAndSaveButton.click();
					System.out.println("'Ignore Duplicate and Save' button clicked directly.");
				} catch (TimeoutException e) {
					System.out.println("'Ignore Duplicate and Save' button not present.");
				}

				// Handle the "Duplicate Lead Confirm" button if it appears
				try {
					wait.until(ExpectedConditions.elementToBeClickable(duplicateLeadPopupConfirmButton));
					duplicateLeadPopupConfirmButton.click();
					System.out.println("'Duplicate Lead Confirm' button clicked directly.");
				} catch (TimeoutException e) {
					System.out.println("Duplicate Lead Confirm button not present.");
				}

				// Exit after handling the save action
				return;

			} catch (ElementClickInterceptedException e) {
				System.out.println("Attempt " + (attempts + 1) + ": Save button click intercepted. Retrying...");
				attempts++;
				try {
					Thread.sleep(1500); // Sleep for 1.5 seconds before retrying
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
					throw new RuntimeException("Thread interrupted during retry wait", ie);
				}

			} catch (Exception e) {
				// Catch any other exceptions and log them
				System.out.println("Unexpected error: " + e.getMessage());
				break; // Exit the loop if an unexpected error occurs
			} finally {
				// Always restore the original implicit wait after handling popups
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}
		}

		// If we exhaust all attempts and the save button is still not clicked, throw an
		// exception
		throw new RuntimeException("Failed to click the Save button after 3 attempts.");
	}

	// Method to type the first name into the input box
	public void searchByFullName() throws InterruptedException {
		// Access the firstName from UserPage class
		// String FullName = .firstName;

		// Ensure the input box is visible before interacting with it
		PageFactory.initElements(driver, this);
		searchUserInputBox.clear();
		waitForVisibility(searchUserInputBox, driver);
		// searchUserInputBox.sendKeys(Salutation+ " " +fullName);
		searchUserInputBox.sendKeys(firstName + " " + lastName);
		// System.out.println("Searched using full name: " + fullName);
		Thread.sleep(3000);
		clickSearchedUser.click();
	}

	public void selectOptionFromHowDidYouHearDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(howDidYouHearDropdownButton);
		howDidYouHearDropdownButton.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	public void selectOptionFromEligibilityDetailsDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(eligibilityDetailsButton);
		eligibilityDetailsButton.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

}
