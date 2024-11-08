package StepDefinitions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	private WebDriver driver;
	public static final Logger log = LogManager.getLogger(CreateQuoteSteps.class);
	BaseTest baseTest = new BaseTest();

	public CreateQuoteSteps() {
		this.driver = Hooks.getDriver(); // Access the driver from Hooks
		this.quote = new quickQuote(driver);
		this.starterExtras = new StarterExtrasEligibilityTest(driver);
	}

	@Given("I am on the quote page")
	public void i_am_on_the_quote_page() {
		log.info("Navigating to the quote page.");
		driver = Hooks.getDriver(); // driver is initialized here
		quote = new quickQuote(driver); // Passed the driver to the quickQuote object

		// Assertion to verify the URL
		baseTest.waitForPageToLoad(driver);
		String expectedUrl = "https://dms.uat.doctorshealthfund.com.au/";
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
		// Verify the visibility of the "Get Quote" button and click it
		// Assert.assertTrue(quote.isGetQuoteButtonVisible(), "Get Quote button is not
		// visible.");
	
		quote.clickGetQuote();
		log.info("'Get Quote' button clicked successfully.");
	}

	
	  @When("I select cover for {string}") public void i_select_cover_for(String
	  coverType) { quote.selectCoverFor(coverType); }// Call the new method with the parameter }
	 

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
	public void i_clicked_on_update_detail_button() {
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

	@Given("I select No for Doctors' Health Fund membership")
	public void i_select_no_for_doctors_health_fund_membership() throws InterruptedException {
		Thread.sleep(2000);
		baseTest.scrollByPixels(450, driver);
		starterExtras.clickMemberStatusNoRadioButton();
	}

	@Given("I select Yes for Australian citizenship or permanent residency")
	public void i_select_yes_for_australian_citizenship_or_permanent_residency() throws InterruptedException {

		starterExtras.clickCitizenshipYesRadioButton();
	}

	@Given("I click on the Health Practitioner button")
	public void i_click_on_the_health_practitioner_button() throws InterruptedException {
		baseTest.scrollByPixels(350, driver);
		starterExtras.clickHealthPractionerBtn();
	}

	@Given("I click on the Medical Practitioner button")
	public void i_click_on_the_Medical_practitioner_button() throws InterruptedException {
		baseTest.scrollByPixels(350, driver);
		starterExtras.clickMedicalPractionerBtn();
	}

	/*
	 * @Given("I select medical practitioner") public void
	 * i_select_medical_practitioner_type(String practitionerType) {
	 * starterExtras.selectRadioButtonByLabel(driver, "Retired doctor"); }
	 */

	@Given("I select {string} as medical practitioner")
	public void i_select_medical_practitioner_type(String practitionerType) {
		starterExtras.selectRadioButtonByLabel(driver, practitionerType);
	}

	@Given("click continue button")
	public void i_click_the_continue_radio_button() throws InterruptedException {
		baseTest.scrollByPixels(350, driver);
		starterExtras.clickContinueButton();
	}

	@Given("I select the option {string} for transferring from another fund")
	public void i_select_transfer_from_another_fund(String response) throws InterruptedException {

		starterExtras.selectTransferFromAnotherFund(response);
		Thread.sleep(2000);
		starterExtras.alertAccept();
	}

	@Given("click next button")
	public void i_click_the_next_btn() throws InterruptedException {
		baseTest.scrollByPixels(150, driver);
		starterExtras.nextButton();
	}

	@Given("I select {string} as the title")
	public void i_select_title(String titleForUser) throws InterruptedException {
		Thread.sleep(2000);
		// baseTest.waitForPageToLoad(driver);
		baseTest.scrollByPixels(-200, driver);
		// baseTest.waitForPageToLoad(driver);

		starterExtras.selectTitle(titleForUser);
	}

	@Given("I enter a random full name")
	public void iEnterARandomFullName() throws InterruptedException {

		starterExtras.enterRandomFullName(driver);
	}

	@Given("I select the gender as {string}")
	public void i_select_the_gender_as(String gender) {
		baseTest.scrollByPixels(250, driver);
		starterExtras.selectGenderRadioButton(gender);
	}

	@When("the user enters and verifies a random email")
	public void userEntersAndVerifiesRandomEmail() {
		baseTest.scrollByPixels(500, driver);
		// Call the method to enter the random email and store it
		String generatedEmail = starterExtras.enterRandomEmail();
		System.out.println("Generated Email: " + generatedEmail);

		Assert.assertTrue(generatedEmail.matches("dhf\\.testing\\+\\d+@gmail\\.com"),
				"Email format validation failed. Generated Email: " + generatedEmail);
	}
	
	@When("the user enters a random phone number")
	public void userEntersRandomPhoneNumber() {
	    starterExtras.enterRandomPhoneNumber();
	}
	
	@When("the user enters and selects the value {string} in the dropdown")
	public void userTypesAndSelectsValue(String inputText) throws InterruptedException {
	    starterExtras.typeAndSelectValue(inputText,driver);
	}

}
