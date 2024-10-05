package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.quickQuote;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateQuoteSteps {
	quickQuote quote;
	BaseTest baseTest = new BaseTest();
	WebDriver driver;

	@Given("I open the browser")
	public void i_open_the_browser() {
		// Initialize the browser
		baseTest.initialize();
        // Retrieve the driver from BaseTest
		driver = baseTest.getDriver();
	}

	@Given("I am on the quote page")
	public void i_am_on_the_quote_page() {
		//Initialize the quickQuote object
		quote = new quickQuote(driver);
       // Assertion to verify the URL
		baseTest.waitForPageToLoad();
		String expectedUrl = "https://uat.doctorshealthfund.com.au/";
		String CurrentUrl = quote.getCurrentURL(); 
		Assert.assertTrue(CurrentUrl.equals(expectedUrl),
				"Unexpected page url! expected [" + expectedUrl + "] but found [" + CurrentUrl + "]");

		// Assertion to verify the page Title
		String expectedTitle = "Private Health Insurance for Doctors | Doctors' Health Fund";
		String actualTitle = quote.getPageTitle();
		Assert.assertTrue(actualTitle.equals(expectedTitle),
				"Unexpected page title! expected [" + expectedTitle + "] but found [" + actualTitle + "]");
	}

	@When("I click the \"Get Quote\" button")
	public void i_click_the_get_quote_button() {
		// Verify the visibility of the "Get Quote" button and click it
		Assert.assertTrue(quote.isGetQuoteButtonVisible(), "Get Quote button is not visible.");
		quote.clickGetQuote();
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
	}

	@Then("I close the browser")
	public void i_close_the_browser() {
		// Close the browser
		baseTest.tearDown();
	}
}