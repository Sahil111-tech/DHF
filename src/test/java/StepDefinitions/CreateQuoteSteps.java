package StepDefinitions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.StarterExtrasEligibilityTest;
import com.qa.airteam.pages.quickQuote;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.qa.airteam.pages.*;

public class CreateQuoteSteps {
	private quickQuote quote;
	StarterExtrasEligibilityTest starterExtras;
	EligibilityAndYourDetailsPage eligibilityAndYourDetails;
	AdditionalInformationPage additionalInfo;
	PaymentPage payment;
	private WebDriver driver;
	public static final Logger log = LogManager.getLogger(CreateQuoteSteps.class);
	BaseTest baseTest = new BaseTest();
	MedicareDetailsPage medicareDetails;
	public static Properties prop;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	// Static block to load properties at the class level
	static {
		try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load properties file: " + e.getMessage());
		}
	}

	public CreateQuoteSteps() {
		this.driver = Hooks.getDriver(); // Access the driver from Hooks
		this.quote = new quickQuote(driver);
		this.starterExtras = new StarterExtrasEligibilityTest(driver);
		this.medicareDetails = new MedicareDetailsPage(driver);
		this.eligibilityAndYourDetails = new EligibilityAndYourDetailsPage(driver);
		this.additionalInfo = new AdditionalInformationPage(driver);
		this.payment = new PaymentPage(driver);
	}

	@Given("I am on the quote page")
	public void i_am_on_the_quote_page() {
		log.info("Navigating to the quote page.");
		driver = Hooks.getDriver(); // driver is initialized here
		quote = new quickQuote(driver); // Passed the driver to the quickQuote object

		// Assertion to verify the URL
		baseTest.waitForPageToLoad(driver);
		String expectedUrl = prop.getProperty("url");
		String CurrentUrl = quote.getCurrentURL();

		Assert.assertTrue(CurrentUrl.equals(expectedUrl),
				"Unexpected page url! expected [" + expectedUrl + "] but found [" + CurrentUrl + "]");

		// Assertion to verify the page Title

		String expectedTitle = "Private Health Insurance for Doctors | Doctors' Health Fund";
		String actualTitle = quote.getPageTitle();
		log.info("Page title: " + actualTitle);
		Assert.assertTrue(actualTitle.equals(expectedTitle),
				"Unexpected page title! expected [" + expectedTitle + "] but found [" + actualTitle + "]");
	}

	@When("I click the \"Get Quote\" button")
	public void i_click_the_get_quote_button() throws InterruptedException {
		log.info("Clicking the 'Get Quote' button.");
		// Thread.sleep(1000);
		quote.clickGetQuote();
		log.info("'Get Quote' button clicked successfully.");
	}

	@When("I select cover for {string}")
	public void i_select_cover_for(String coverType) {
		quote.selectCoverFor(coverType);
	}// Call the new method with the parameter }

	@When("I click the \"Next\" button")
	public void i_click_the_next_button() {
		quote.clickNext();
	}

	@When("I select the residence as {string}")
	public void i_select_the_residence_as(String residence) {
		quote.selectResidence(residence); // Call the new method with the parameter
	}

	@When("I click the \"Next\" button again")
	public void i_click_the_next_button_again() {
		quote.clickNext();
	}

	@When("I enter my date of birth as {string}")
	public void i_enter_my_date_of_birth_as(String dob) {
		String[] dateParts = dob.split("/");
		quote.enterDateOfBirth(dateParts[0]);
		quote.enterMonthOfBirth(dateParts[1]);
		quote.enterYearOfBirth(dateParts[2]);

	}

	@When("I select the annual income as {string}")
	public void i_select_the_annual_income_as(String salary) {
		quote.selectAnnualIncome(salary); // Call the new method with the parameter
	}

	@When("I click the \"Calculate Cover\" button")
	public void i_click_the_calculate_cover_button() {
		quote.clickCalculateCover();
	}

	@Then("I select the hospital cover as {string}")
	public void iSelectTheHospitalPlanAs(String coverType) {
		baseTest.scrollByPixels(500, driver);
		quote.selectHospitalCover(coverType);
	}

	@Then("I select the extras cover as {string}")
	public void iSelectTheExtrasCoverAs(String coverType) {
		baseTest.scrollByPixels(100, driver);
		quote.selectExtrasCover(coverType);
	}

	@Then("I should see the title {string}")
	public void i_should_see_the_title(String expectedTitle) throws InterruptedException {
		String actualTitle = quote.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		// Scroll to the middle of the page
		BaseTest.scrollDownBy500(driver);
	}

	@Given("the_user_selects_plan {string}")
	public void the_user_selects(String planName) throws InterruptedException {
		log.info("Selecting the plan: " + planName);
		starterExtras = new StarterExtrasEligibilityTest(driver);
		// Added a little upward scroll before selecting the plans
		if ("Prime Choice".equals(planName) || "Top Cover".equals(planName)) {
			BaseTest.scrollUpBy400(driver);

		}
		switch (planName) {
		case "Smart Starter Bronze Plus":
			baseTest.safelyClickElement(starterExtras.getSmartStarterBronzePlusOption(), driver);
			break;
		case "Prime Choice":

			baseTest.safelyClickElement(starterExtras.getPrimeChoice(), driver);
			break;
		case "Top Cover":
			baseTest.safelyClickElement(starterExtras.getTopCover(), driver);
			break;
		default:
			throw new IllegalArgumentException("Invalid plan name: " + planName);
		}
	}

	@When("the user selects \"Starter Extras\"")
	public void the_user_selects_starter_extras_option() {
		log.info("Selecting 'Starter Extras'.");
		baseTest.scrollDownBy500(driver);
		starterExtras.selectStarterExtras();
	}

	@Then("\"Starter Extras\" should be enabled")
	public void starter_extras_should_be_enabled() {
		log.info("Verifying 'Starter Extras' is enabled.");
		Assert.assertTrue(starterExtras.isStarterExtrasEnabled(), "Starter Extras should be enabled.");
	}

	@When("the user attempts to select \"Starter Extras\"")
	public void the_user_attempts_to_select_starter_extras() {
		baseTest.scrollDownBy500(driver);
		log.info("Attempting to select 'Starter Extras'.");
		starterExtras.selectStarterExtras();
	}

	@Then("\"Starter Extras\" should be disabled")
	public void starter_extras_should_be_disabled() {
		log.info("Verifying 'Starter Extras' is disabled.");
		Assert.assertTrue(starterExtras.isStarterExtrasDisabled(), "Starter Extras should be disabled.");
	}

	/***********************
	 * 2nd Scenario initiated from here
	 ****************************/

	@Given("I clicked on the coverage type dropdown and selected {string}.")
	public void i_clicked_On_cover_type_dropdown(String coverType) throws InterruptedException {
		baseTest.scrollToTop(driver);
		starterExtras.selectCoverType(coverType);
	}

	/***********************
	 * 3rd Scenario initiated from her
	 ****************************/
	@Given("I clicked on edit button")
	public void i_clicked_on_edit_button() throws InterruptedException {
		baseTest.scrollToTop(driver);
		starterExtras.clickEditButton();
	}

	@Given("I clicked on the coverage for dropdown and selected {string}")
	public void i_clicked_on_coverage_type_dropdown_and_selected(String coverageFor) throws InterruptedException {
		starterExtras.selectCoverFor(coverageFor);
	}

	@When("I enter my partner date of birth as {string}")
	public void i_enter_my_partners_date_of_birth_as(String dob) {
		String[] dateParts = dob.split("/");
		starterExtras.enterPartnerDateOfBirth(dateParts[0]);
		starterExtras.enterPartnerMonthOfBirth(dateParts[1]);
		starterExtras.enterPartnerYearOfBirth(dateParts[2]);

	}

	@Given("I clicked on update details button")
	public void i_clicked_on_update_detail_button() throws InterruptedException {
		Thread.sleep(1500);
		starterExtras.clickUpdateDetails();
	}

	@When("the user clicks on {string}")
	public void the_user_clicks_on(String buttonName) {
		baseTest.scrollDownBy500(driver);
		if (buttonName.equals("Choose Covers")) {
			starterExtras.clickChooseCovers();
		}
	}

	/*************************
	 * Steps for submission end to end MAF application
	 ****************************************/
	// click apply now button

	@Given("I clicked on Apply Now button")
	public void i_clicked_on_apply_now_button() {
		baseTest.scrollDownBy500(driver);
		starterExtras.clickApplyNowButton();
	}

	@Given("I click the Apply Now button for the selected plan")
	public void i_click_the_apply_now_button_for_selected_plan() {
		// Scroll to make the button visible
		baseTest.scrollByPixels(200, driver);

		// Click the Apply Now button
		quote.clickApplyNowButton();

		log.info("Clicked on the Apply Now button for the selected plan.");
	}

	@Given("I select No for Doctors' Health Fund membership")
	public void i_select_no_for_doctors_health_fund_membership() throws InterruptedException {

		/*
		 * Thread.sleep(2000); baseTest.scrollByPixels(450, driver);
		 * starterExtras.clickMemberStatusNoRadioButton();
		 */
		baseTest.scrollByPixels(450, driver);
		// baseTest.waitForElementToBeClickable(driver,
		// starterExtras.clickMemberStatusNoRadioButton());
		eligibilityAndYourDetails.clickMemberStatusNoRadioButton();

	}

	@Given("I select Yes for Australian citizenship or permanent residency")
	public void i_select_yes_for_australian_citizenship_or_permanent_residency() throws InterruptedException {
		eligibilityAndYourDetails.clickCitizenshipYesRadioButton();
	}

	@Given("I click on the Health Practitioner button")
	public void i_click_on_the_health_practitioner_button() throws InterruptedException {
		baseTest.scrollByPixels(350, driver);
		starterExtras.clickHealthPractionerBtn();
	}

	@Given("I click on the Medical Practitioner button")
	public void i_click_on_the_Medical_practitioner_button() throws InterruptedException {
		baseTest.scrollByPixels(350, driver);
		eligibilityAndYourDetails.clickMedicalPractionerBtn();
	}

	@Given("I select {string} as medical practitioner")
	public void i_select_medical_practitioner_type(String practitionerType) {
		eligibilityAndYourDetails.selectRadioButtonByLabel(driver, practitionerType);
	}

	@Given("I submit the eligibility page.")
	public void i_click_the_continue_button() throws InterruptedException, AWTException {

		// baseTest.scrollByPixels(-100, driver);
		baseTest.scrollUpBy400(driver);
		Thread.sleep(1000);
		eligibilityAndYourDetails.clickContinueButton();
		// Thread.sleep(1000);

	}

	@Given("I select the option {string} for transferring from another fund")
	public void i_select_transfer_from_another_fund(String response) throws InterruptedException {
		// Thread.sleep(2000);
		eligibilityAndYourDetails.selectTransferFromAnotherFund(response);
		Thread.sleep(700);
		eligibilityAndYourDetails.alertAccept();
	}

	@Given("click next button")
	public void i_click_the_next_btn() throws InterruptedException {
		baseTest.scrollByPixels(150, driver);
		eligibilityAndYourDetails.nextButton();
	}

	@Given("I select {string} as the title")
	public void i_select_title(String titleForUser) throws InterruptedException {
		// Thread.sleep(2000);
		// baseTest.waitForPageToLoad(driver);
		baseTest.scrollByPixels(-200, driver);
		// baseTest.waitForPageToLoad(driver);

		eligibilityAndYourDetails.selectTitle(titleForUser);
	}

	@Given("I enter a random full name")
	public void iEnterARandomFullName() throws InterruptedException {

		eligibilityAndYourDetails.enterRandomFullName(driver);
	}

	@Given("I select the gender as {string}")
	public void i_select_the_gender_as(String gender) {
		baseTest.scrollByPixels(250, driver);
		eligibilityAndYourDetails.selectGenderRadioButton(gender);
	}

	@When("the user enters and verifies a random email")
	public void userEntersAndVerifiesRandomEmail() {
		baseTest.scrollByPixels(500, driver);
		// Call the method to enter the random email and store it
		String generatedEmail = eligibilityAndYourDetails.enterRandomEmail();
		System.out.println("Generated Email: " + generatedEmail);

		Assert.assertTrue(generatedEmail.matches("dhf\\.testing\\+\\d+@gmail\\.com"),
				"Email format validation failed. Generated Email: " + generatedEmail);
	}

	@When("the user enters a random phone number")
	public void userEntersRandomPhoneNumber() {
		eligibilityAndYourDetails.enterRandomPhoneNumber();
	}

	@When("the user enters and selects the value {string} in the dropdown")
	public void userTypesAndSelectsValue(String inputText) throws InterruptedException {
		Thread.sleep(1500);
		eligibilityAndYourDetails.typeAndSelectValue(inputText, driver);
		Thread.sleep(1000);
	}

	// Step definition for selecting the "Are you an existing Avant member?" radio
	// button (Yes/No)
	@When("I select the {string} radio button for existing Avant member")
	public void i_select_the_radio_button_for_existing_Avant_member(String option) {
		// Call the method from Page Object to select the radio button
		baseTest.scrollByPixels(100, driver);
		eligibilityAndYourDetails.selectExistingAvantMemberOption(option); // Pass "Yes" or "No"

	}

	// Step to click the Continue button on the Your Details page
	@Given("I submit the Your Details page")
	public void i_submit_your_details_page() throws InterruptedException {
		/*
		 * try { // Wait for the "Continue" button to be visible and clickable
		 * //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.visibilityOf(eligibilityAndYourDetails.
		 * clickContinueButton()));
		 * wait.until(ExpectedConditions.elementToBeClickable(eligibilityAndYourDetails.
		 * clickContinueButton()));
		 * 
		 * // Scroll to ensure the button is in view baseTest.scrollUpBy400(driver);
		 * 
		 * // Retry clicking if intercepted try {
		 * eligibilityAndYourDetails.clickContinueButton(); } catch
		 * (ElementClickInterceptedException e) {
		 * log.warn("Click intercepted. Retrying after scrolling...");
		 * //baseTest.scrollByPixels(50, driver);
		 * eligibilityAndYourDetails.clickContinueButton(); }
		 * 
		 * log.info("Successfully submitted the 'Your Details' page."); } catch
		 * (Exception e) { log.error("Error while submitting the 'Your Details' page: "
		 * + e.getMessage()); throw e; }
		 */

		Actions actions = new Actions(driver);
		Thread.sleep(1000);
		baseTest.scrollByPixels(150, driver);
		WebElement continueButton = eligibilityAndYourDetails.clickContinueButton();

		// Use Actions class for double-click Actions actions = new Actions(driver);
		actions.doubleClick(continueButton).perform(); // Perform the double-click action
		Thread.sleep(1000);

	}

	/*
	 * WebElement continueButton = eligibilityAndYourDetails.clickContinueButton();
	 * 
	 * // Scroll into view ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
	 * 
	 * // Handle potential element obstruction try { continueButton.click();
	 * System.out.println("Clicked on Continue button."); } catch
	 * (ElementClickInterceptedException e) {
	 * System.out.println("Click intercepted. Retrying with JavaScript.");
	 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
	 * continueButton); }
	 * 
	 * Thread.sleep(1000); }
	 */

	@When("I select {string} for the question \"Are all the people on this policy eligible for Medicare?\"")
	public void i_select_option_for_medicare_eligibility(String option) {
		medicareDetails.selectMedicareEligibility(option);
	}

	@When("I verify images against green and blue medicare options")
	public void iVerifyImagesAgainstGreenAndBlueMedicareOptions() {
		medicareDetails.verifyImagesForGreenAndBlueOptions();
	}

	@When("I enter {string} in the card number field")
	public void i_enter_in_the_card_number_field(String cardNumber) {
		medicareDetails.enterCardNumber(cardNumber);
	}

	@When("I enter {string} as the Card Reference Number")
	public void iEnterAsTheCardReferenceNumber(String number) {
		medicareDetails.enterCardReferenceNumber(number);
	}

	@When("I enter {string} as the day, {string} as the month and {string} as the year")
	public void iEnterAsTheMonthAndAsTheYear(String day, String month, String year) {
		medicareDetails.enterValidityDetails(day, month, year);
	}

	@When("I select {string} for claiming the Australian Government Rebate")
	public void i_select_for_claiming_the_Australian_Government_Rebate(String option) {
		medicareDetails.selectRebateOption(option);
	}

	/**
	 * Step to select the policy coverage option.
	 * 
	 * @param option The option to select ("Yes" or "No").
	 */
	@When("I select {string} for policy coverage")
	public void i_select_for_policy_coverage(String option) {
		baseTest.scrollByPixels(200, driver);
		medicareDetails.selectPolicyCoverageOption(option);

	}

	@When("I {string} the checkbox")
	public void i_toggle_the_checkbox(String action) throws InterruptedException {
		Thread.sleep(3000);
		baseTest.scrollByPixels(850, driver);
		if (action.equalsIgnoreCase("select")) {
			medicareDetails.toggleCheckbox(true);
		} else if (action.equalsIgnoreCase("deselect")) {
			medicareDetails.toggleCheckbox(false);
		} else {
			throw new IllegalArgumentException("Invalid action: " + action + ". Use 'select' or 'deselect'.");
		}
	}

	@Given("I submit the Medicare Details page")
	public void i_submit_Medicare_details_page() throws InterruptedException {

		baseTest.scrollByPixels(150, driver);
		WebElement continueButton = medicareDetails.clickContinueButton();
		Actions actions = new Actions(driver);
		actions.doubleClick(continueButton).perform(); // Perform the double-click action

	}

	@When("I select {string} for the question Do you wish to nominate a person other than yourself as an authorised person on the policy?")
	public void i_select_choice_for_the_question(String choice) throws InterruptedException {
		baseTest.scrollToTop(driver);
		// Thread.sleep(2000);
		additionalInfo.selectRadioButton(choice);
	}

	@When("I select {string} for the Avant Group Member option")
	public void iSelectForTheAvantGroupMemberOption(String option) {
		additionalInfo.selectAvantGroupMemberRadioButton(option);
	}

	// Step to select a value from the "howDidYouHearAboutUs" dropdown
	@When("I select {string} from the How Did You Hear About Us dropdown")
	public void iSelectFromTheHowDidYouHearAboutUsDropdown(String option) {
		additionalInfo.selectHowDidYouHearAboutUsOption(option);
	}

	// Step to select the Yes/No radio button for "Would you like us to check in
	// with you after you join?"
	@When("I select {string} for the check-in radio button after joining")
	public void iSelectCheckInRadioButtonAfterJoining(String option) {
		baseTest.scrollByPixels(200, driver);
		additionalInfo.selectCheckInRadioButton(option);
	}

	// Step to select or deselect the "I have read and agree to the Terms and
	// Conditions of Service."
	@When("I {string} the Terms and Conditions checkbox")
	public void iSelectTermsAndConditionsCheckbox(String action) {
		baseTest.scrollByPixels(300, driver);
		boolean select = action.equalsIgnoreCase("accept");
		additionalInfo.selectTermsAndConditionsCheckbox(select);
	}

	// Step to select or deselect "I agree to The Doctors' Health Fund using my
	// personal information."
	@When("I {string} the agree using personal information checkbox")
	public void iSelectAgreeUsingPersonalInformationCheckbox(String action) {
		boolean select = action.equalsIgnoreCase("agree");
		additionalInfo.selectAgreeUsingPersonalInformationCheckbox(select);
	}

	// Step to select or deselect "I have read and agree to the AHSA Privacy
	// Policy."
	@When("I {string} the AHSA Privacy Policy checkbox")
	public void iSelectAhsaPrivacyPolicyCheckbox(String action) {
		boolean select = action.equalsIgnoreCase("accept");
		additionalInfo.selectAhsaPrivacyPolicyCheckbox(select);
	}

	// Step to select or deselect "The information entered in this application is
	// true and correct."
	@When("I {string} the application truth checkbox")
	public void iSelectConfirmApplicationIsTrueCheckbox(String action) {
		boolean select = action.equalsIgnoreCase("confirm");
		additionalInfo.selectConfirmApplicationIsTrueCheckbox(select);
	}

	@Given("I submit the additional information page")
	public void i_submit_additional_information_page() throws InterruptedException {
		baseTest.scrollByPixels(100, driver);
		Thread.sleep(1000);
		additionalInfo.clickContinueButton();
		Thread.sleep(1000);
	}

	@When("^I enter the BSB number \"([^\"]*)\"$")
	public void iEnterTheBSBNumber(String bsbNumber) {
		payment.enterBSB(bsbNumber);
	}

	@Given("^I enter a valid account number \"([^\"]*)\"$")
	public void enterAccountNumber(String accountNumber) {
		payment.enterAccountNumber(accountNumber); // Calls the method from the Page Object to enter the account
													// number.
	}

	@When("I enter a random account name")
	public void iEnterARandomAccountName() {
		baseTest.scrollByPixels(150, driver);
		payment.enterRandomAccountName(); // Calling the method from the medicare details PageObject
	}

	// Step definition for clicking on the Direct Debit Agreements checkbox
	@When("I select the direct debit agreement checkbox")
	public void iSelectTheDirectDebitAgreementCheckbox() {
		baseTest.scrollByPixels(150, driver);
		payment.clickDirectDebitCheckbox();
	}

	// Step to click on the Submit Form button
	@When("I click on the submit form button")
	public void iClickOnTheSubmitFormButton() throws InterruptedException {
		baseTest.scrollByPixels(150, driver);
		Thread.sleep(1000);
		payment.clickSubmitFormButton(); // Call the action method
	}

	@When("I toggle the bank account details checkbox by double-clicking")
	public void iToggleTheBankAccountDetailsCheckboxByDoubleClicking() throws InterruptedException {
		// baseTest.scrollByPixels(250, driver);
		payment.toggleCheckboxByDoubleClick(); // Call the action method

	}

	@Then("I verify that the thank-you message is displayed")
	public void i_verify_thank_you_message_is_displayed() throws InterruptedException {
		Thread.sleep(2000);
		boolean isMessageDisplayed = payment.isThankYouMessageDisplayed(); // Check if the message is displayed
		Assert.assertTrue(isMessageDisplayed, "The thank-you message is not displayed as expected."); // TestNG
																										// assertion
		log.info("The thank-you message is displayed successfully.");
	}
}
