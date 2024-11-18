package StepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.MedicareDetailsPage;
import com.qa.airteam.pages.StarterExtrasEligibilityTest;
import com.qa.airteam.pages.quickQuote;
import com.qa.sugarcrm.pages.LeadsPage;
import com.qa.sugarcrm.pages.loginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SugarSteps {
//	    private quickQuote quote;
//		StarterExtrasEligibilityTest starterExtras;
	private loginPage lp;
	private LeadsPage leadsPage;
	private WebDriver driver;
	public static final Logger log = LogManager.getLogger(CreateQuoteSteps.class);
	BaseTest baseTest = new BaseTest();

	public SugarSteps() {
		this.driver = Hooks.getDriver(); // Access the driver from Hooks
		this.lp = new loginPage(driver);
		this.leadsPage=new LeadsPage(driver);
	}

	@Given("I launch the application URL")
	public void i_am_on_the_login_page() {
		log.info("Navigating to the login page.");
		driver = Hooks.getDriver(); // driver is initialized here
		lp = new loginPage(driver); // Passed the driver to the login page object
		// Assertion to verify the URL
		baseTest.waitForPageToLoad(driver);
		String expectedUrl = "https://crmtest.doctorshealthfund.com.au/";
		String CurrentUrl = lp.getCurrentURL();

		Assert.assertTrue(CurrentUrl.equals(expectedUrl),
				"Unexpected page url! expected [" + expectedUrl + "] but found [" + CurrentUrl + "]");

	}

	@When("I click on the Show log in form link")
	public void iClickOnTheShowLogInFormLink() {
		lp.clickShowLogInForm();
	}

	@When("I enter the username {string}")
	public void iEnterTheUsername(String username) {
		lp.enterUsername(username);
	}

	@When("I enter the password {string}")
	public void iEnterThePassword(String password) {
		lp.enterPassword(password);
	}

	@When("I click on the login button")
	public void iClickOnTheLoginButton() {
		lp.clickLoginButton();
	}

	@Then("I should see the page title as {string}")
	public void iShouldSeeThePageTitleAs(String expectedTitle) {
		String actualTitle = lp.getPageTitle();
		//System.out.println("Actual Page Title is = " + actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle,
				"Assertion Failed: Expected Title is '" + expectedTitle + "' but Found '" + actualTitle + "'");
	}
	

    @Then("I click on the Leads button")
    public void iClickOnTheLeadsButton() {
        leadsPage.clickLeadsButton();
    }
    
    @Then("I click on the Create button")
    public void iClickOnTheCreateButton() {
    	leadsPage.clickCreateButton();
    }
    
    @When("I select salutation as {string}")
    public void iSelectSalutationAs(String salutation) {
        leadsPage.selectSalutation(salutation);
    }

}
