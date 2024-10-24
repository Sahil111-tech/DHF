package StepDefinitions;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.StarterExtrasEligibilityTest;
import com.qa.airteam.pages.quickQuote;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateQuoteSteps {
	private quickQuote quote;
	private StarterExtrasEligibilityTest starterExtras;
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
	public void i_click_the_get_quote_button() {
		log.info("Clicking the 'Get Quote' button.");
		// Verify the visibility of the "Get Quote" button and click it
		Assert.assertTrue(quote.isGetQuoteButtonVisible(), "Get Quote button is not visible.");
		quote.clickGetQuote();
		log.info("'Get Quote' button clicked successfully.");
	}

	@When("I select cover for a single person")
	public void i_select_cover_for_a_single_person() {
		quote.selectCoverForSingle();
	}

	@When("I click the \"Next\" button")
	public void i_click_the_next_button() {
		quote.clickNext();
	}

	@When("I select the residence as \"NSW\"")
	public void i_select_the_residence_as_nsw() {
		quote.clickResidenceNSW();
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

	@When("I select my annual income")
	public void i_select_my_annual_income() {
		quote.selectAnnualIncomeOption();
	}

	@When("I click the \"Calculate Cover\" button")
	public void i_click_the_calculate_cover_button() {
		quote.clickCalculateCover();
	}

	@Then("I should see the title {string}")
	public void i_should_see_the_title(String expectedTitle) {
		String actualTitle = quote.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		// Scroll to the middle of the page
		BaseTest.scrollDownBy500(driver);
	}

	@Given("the_user_selects_plan {string}")
	public void the_user_selects(String planName) {
		log.info("Selecting the plan: " + planName);
		starterExtras = new StarterExtrasEligibilityTest(driver);
		// Added a little upward scroll before selecting the plans
		if ("Prime Choice".equals(planName) || "Top Cover".equals(planName)) {
			BaseTest.scrollUpBy400(driver);

		}
		switch (planName) {
		case "Smart Starter Bronze Plus":
			starterExtras.selectSmartStarterBronzePlus();
			break;
		case "Prime Choice":

			starterExtras.selectPrimeChoice();
			break;
		case "Top Cover":
			starterExtras.selectTopCover();
			break;
		default:
			throw new IllegalArgumentException("Invalid plan name: " + planName);
		}
	}

	@When("the user selects \"Starter Extras\"")
	public void the_user_selects_starter_extras_option() {
		log.info("Selecting 'Starter Extras'.");
		starterExtras.selectStarterExtras();
	}

	@Then("\"Starter Extras\" should be enabled")
	public void starter_extras_should_be_enabled() {
		log.info("Verifying 'Starter Extras' is enabled.");
		Assert.assertTrue(starterExtras.isStarterExtrasEnabled(), "Starter Extras should be enabled.");
	}

	@When("the user attempts to select \"Starter Extras\"")
	public void the_user_attempts_to_select_starter_extras() {
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

	@Given("I clicked on the cover type dropdown and selected Extras only.")
	public void i_clicked_On_cover_type_dropdown() throws InterruptedException {
		starterExtras.selectExtrasIfAvailable();
	}
}