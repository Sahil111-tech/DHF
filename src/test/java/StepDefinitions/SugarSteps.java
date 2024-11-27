package StepDefinitions;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.BaseTest;
import com.qa.airteam.pages.MedicareDetailsPage;
import com.qa.airteam.pages.StarterExtrasEligibilityTest;
import com.qa.airteam.pages.quickQuote;
import com.qa.sugarcrm.pages.AffiliationsTabPage;
import com.qa.sugarcrm.pages.ComplianceTabPage;
import com.qa.sugarcrm.pages.FinancialInfoPage;
import com.qa.sugarcrm.pages.GenerateQuotePage;
import com.qa.sugarcrm.pages.LeadsAndPersonalDetailsPage;
import com.qa.sugarcrm.pages.MedicareTabPage;
import com.qa.sugarcrm.pages.PreviousHealthFundPage;
import com.qa.sugarcrm.pages.WrapUpTabPage;
import com.qa.sugarcrm.pages.loginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SugarSteps {
//	    private quickQuote quote;
//		StarterExtrasEligibilityTest starterExtras;
	private loginPage lp;
	private LeadsAndPersonalDetailsPage leadsPage;
	private PreviousHealthFundPage previousHealthFund;
	private MedicareDetailsPage medicareDetailsPage;
	private MedicareTabPage medicareTabPage;
	private ComplianceTabPage complianceTab;
	private AffiliationsTabPage affiliationsTab;
	private GenerateQuotePage generateQuote;
	private FinancialInfoPage financialInfoPage;
	private WrapUpTabPage wrapUpPage;
	private WebDriver driver;
	public static final Logger log = LogManager.getLogger(CreateQuoteSteps.class);
	BaseTest baseTest = new BaseTest();
	public static Properties prop;
    // Static block to load properties at the class level
    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file: " + e.getMessage());
        }
    }

	public SugarSteps() {
		this.driver = Hooks.getDriver(); // Access the driver from Hooks
		this.lp = new loginPage(driver);
		this.leadsPage = new LeadsAndPersonalDetailsPage(driver);
		this.previousHealthFund = new PreviousHealthFundPage(driver);
		this.medicareDetailsPage = new MedicareDetailsPage(driver);
		this.medicareTabPage = new MedicareTabPage(driver);
		this.complianceTab = new ComplianceTabPage(driver);
		this.affiliationsTab = new AffiliationsTabPage(driver);
		this.generateQuote = new GenerateQuotePage(driver);
		this.financialInfoPage = new FinancialInfoPage(driver);
		this.wrapUpPage=new WrapUpTabPage(driver);
	}

	@Given("I launch the application URL")
	public void i_am_on_the_login_page() {
		log.info("Navigating to the login page.");
		driver = Hooks.getDriver(); // driver is initialized here
		lp = new loginPage(driver); // Passed the driver to the login page object
		// Assertion to verify the URL
		baseTest.waitForPageToLoad(driver);
		String expectedUrl = prop.getProperty("crmurl");
		String CurrentUrl = lp.getCurrentURL();

		Assert.assertTrue(CurrentUrl.equals(expectedUrl),
				"Unexpected page url! expected [" + expectedUrl + "] but found [" + CurrentUrl + "]");

	}

	@When("I click on the Show log in form link")
	public void iClickOnTheShowLogInFormLink() {
		lp.clickShowLogInForm();
	}

	// Action method to enter the username
	@When("I enter the username {string}")
	public void iEnterTheUsername(String username) {
		lp.enterUsername(username);
	}

	// Action method to enter the password
	@When("I enter the password {string}")
	public void iEnterThePassword(String password) {
		lp.enterPassword(password);
	}

	// Action method to click on log in button
	@When("I click on the login button")
	public void iClickOnTheLoginButton() {
		lp.clickLoginButton();
	}

	// Assertion to verify the page title after logs in
	@Then("I should see the page title as {string}")
	public void iShouldSeeThePageTitleAs(String expectedTitle) {
		String actualTitle = lp.getPageTitle();
		// System.out.println("Actual Page Title is = " + actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle,
				"Assertion Failed: Expected Title is '" + expectedTitle + "' but Found '" + actualTitle + "'");
	}

	// Step method to click on Leads button
	@Then("I click on the Leads button")
	public void iClickOnTheLeadsButton() {
		leadsPage.clickLeadsButton();
	}

	// Step method to click on create button
	@Then("I click on the Create button")
	public void iClickOnTheCreateButton() {

		leadsPage.clickCreateButton();
	}

	/*
	 * //Step method for salutation (Mr.,Mrs etc.)
	 * 
	 * @When("I select salutation as {string}") public void
	 * iSelectSalutationAs(String salutation) {
	 * leadsPage.selectSalutation(salutation); }
	 */

	@When("I select {string} from the Salutation dropdown")
	public void userSelectsSalutationFromDropdown(String salutation) {
		// Call the method to select the salutation
		leadsPage.selectSalutation(salutation);
	}

	// Step method for First,middle and last name
	@When("the user enters a random full name into the input fields")
	public void theUserEntersARandomFullNameIntoTheInputFields() {
		leadsPage.enterRandomFullName(driver); // Call the method to enter a random full name
	}

	// Step method for Brand dropdown
	@When("the user selects {string} from the Brand dropdown")
	public void theUserSelectsFromTheDropdown(String valueToSelect) {
		leadsPage.selectValueFromBrandDropdown(valueToSelect);
	}

	// Step method for Lead Source dropdown
	@When("the user selects {string} from the Lead Source dropdown")
	public void theUserSelectsFromTheLeadSourceDropdown(String valueToSelect) {
		leadsPage.selectValueFromLeadSourceDropdown(valueToSelect);
	}

	// Step method for Lead Type dropdown
	@When("the user selects {string} from the Lead Type dropdown")
	public void theUserSelectsFromTheLeadTypeDropdown(String valueToSelect) {
		leadsPage.selectValueFromLeadTypeDropdown(valueToSelect);
	}

	// Step method for the PR/Citizen checkbox
	@When("I select the PRCitizen checkbox")
	public void iSelectThePRCitizenCheckbox() {
		// Calling the action method to select the checkbox
		leadsPage.selectPRCitizenCheckbox();
	}

	// Step method for Eligibility ID dropdown
	@When("the user selects {string} from the Eligibility ID dropdown")
	public void theUserSelectsFromTheEligibilityIdDropdown(String valueToSelect) {
		leadsPage.selectValueFromEligibilityIdDropdown(valueToSelect);
	}

	@When("the user enters {string} as the birth date")
	public void theUserEntersBirthDate(String dateOfBirth) {
		leadsPage.enterBirthDate(dateOfBirth);
	}

	// To generate the random phone number
	@When("I generate and enter a random phone number")
	public void iGenerateAndEnterARandomPhoneNumber() {
		// Calling the action method to generate and enter the random phone number
		leadsPage.enterRandomPhoneNumber();
	}

	@When("the user enters a random email")
	public void userEntersAndVerifiesRandomEmail() {
		baseTest.scrollByPixels(500, driver);
		// Call the method to enter the random email and store it
		String generatedEmail = leadsPage.enterRandomEmail();
		System.out.println("Generated Email: " + generatedEmail);

		Assert.assertTrue(generatedEmail.matches("dhf\\.testing\\+\\d+@gmail\\.com"),
				"Email format validation failed. Generated Email: " + generatedEmail);
	}

	@When("User types {string} in the suggestion input box and selects the {int} option using keyboard")
	public void userHandlesDropdownWithKeyboard(String inputText, int stepsToTraverse) throws InterruptedException {
		leadsPage.handleDynamicDropdownWithKeyboard(inputText, stepsToTraverse);
	}

	@When("the user selects {string} from the Agent Lead Score dropdown")
	public void userSelectsFromTheAgentLeadScoreDropdown(String valueToSelect) {
		leadsPage.selectAgentLeadScoreOption(valueToSelect);

	}

	// Steps for Eligibility details dropdown
	@When("the user selects {string} from the Eligibility details dropdown")
	public void userSelectsFromTheEligibilityDetailsDropdown(String valueToSelect) {
		leadsPage.selectEligibilityDetailsOption(valueToSelect);

	}


	@When("the user click on the how did you hear about us dropdown and selects {int} 'DHF Sales Specialist' from the dropdown")
	public void selectOptionFromHowDidYouHearAboutUsDropdown(int stepsToTraverse) throws InterruptedException {
		leadsPage.selectOptionFromHowDidYouHearDropdown(stepsToTraverse);

	}

	// I clicked on the save button
	@Then("I click on the Save button")
	public void iClickOnTheSaveButton() {
		leadsPage.clickSaveButton(); // Call the method from the Page Object class
	}

	@When("^the user navigates to the Previous Health Fund tab$")
	public void navigateToPreviousHealthFundTab() {
		previousHealthFund.switchToPreviousHealthFundTab(); // Call the action method
		System.out.println("User navigated to the Previous Health Fund tab.");
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the Previous Health Fund dropdown and select {int} 'ACA Health Benifits Fund' from the dropdownn")
	public void userSelectsFromThePreviousHealthFundDropdown(int valueToSelect) {
		previousHealthFund.selectPreviousHealthFundValue(valueToSelect);

	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the Gender dropdown and traverses {int} step")
	public void selectOptionFromGenderDropdownStep(int stepsToTraverse) throws InterruptedException {

		// Call the action method with the input text and steps to traverse
		leadsPage.selectOptionFromGenderDropdown(stepsToTraverse);
	}

	@When("the user click on the Eligibility details and select {int} 'CMO' from the dropdown")
	public void selectOptionFromEligibilityDetailsDropdown(int stepsToTraverse) throws InterruptedException {
		leadsPage.selectOptionFromEligibilityDetailsDropdown(stepsToTraverse);

	}

	@When("I select 'DHF Sales Specialist' from the dropdown")
	public void iSelectDHFSalesSpecialistFromTheDropdown() {
		// Call the action method to perform the operation
		leadsPage.selectDHFSalesSpecialist();
	}

	@When("^I check the Cooling off checkbox$")
	public void checkCoolingOffCheckboxStep() {
		previousHealthFund.checkCoolingOffCheckbox(); // Invoke the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the Previous Fund Transfer dropdown and traverses {int} step")
	public void previousFundTransferStep(int stepsToTraverse) throws InterruptedException {
		previousHealthFund.selectOptionFromPreviousHealthFundDropdown(stepsToTraverse); // Invoke the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the 31st birthday dropdown and traverses {int} 'Yes' step")
	public void sinceYour31stBirthdayStep(int stepsToTraverse) throws InterruptedException {
		previousHealthFund.selectOptionFromSinceYour31stBirthdayDropdown(stepsToTraverse); // Invoke the action method
	}

	@When("I enter a random Health Fund Membership ID")
	public void enterRandomHealthFundMembershipId() {
		previousHealthFund.enterHealthFundMembershipId();
	}

	@When("I enter a policy name")
	public void enterPolicyName() {
		previousHealthFund.enterPolicyName();
	}

	@When("I click on the first user fullname in the table")
	public void clickOnFirstUserFullname() throws InterruptedException {
		Thread.sleep(8000);
		previousHealthFund.clickOnFirstUserFullname();
	}

	@When("I click on the Medicare button")
	public void clickOnMedicareButton() {
		medicareTabPage.clickMedicareButton();
	}

	@When("I click on the Edit button")
	public void clickOnEditButton() throws InterruptedException {
		// Thread.sleep(3000);
		medicareTabPage.clickEditButton();

	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the medical card type dropdown and traverses {int} 'Green' step")
	public void medicalCardTypeStep(int stepsToTraverse) throws InterruptedException {
		medicareTabPage.selectOptionFromMedicalCardTypeDropdown(stepsToTraverse); // Invoke the action method
	}

	@When("I enter the Medicare card number {string}")
	public void iEnterTheMedicareCardNumber(String cardNumber) {
		try {
			medicareTabPage.enterMedicareCardNumber(cardNumber);
		} catch (IllegalArgumentException e) {
			fail("Invalid input: " + e.getMessage());
		}
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the medical expiry month dropdown and traverses {int}th step")
	public void medicalExpiryMonthStep(int stepsToTraverse) throws InterruptedException {
		medicareTabPage.selectOptionFromMedicalExpiryMonthDropdown(stepsToTraverse); // Invoke the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the applicant claiming rebate dropdown and traverses {int}nd step")
	public void isApplicantClaimingRebateStep(int stepsToTraverse) throws InterruptedException {
		medicareTabPage.isApplicantClaimingRebateDropdownValues(stepsToTraverse); // Invoke the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the all people hold policy dropdown and traverses {int}st step")
	public void isAllPeopleHoldPolicyStep(int stepsToTraverse) throws InterruptedException {
		medicareTabPage.isAllPeopleHoldPolicyDropdownValues(stepsToTraverse); // Invoke the action method
	}

	// Step to enter the Medicare card expiry year
	@When("^I enter the Medicare card expiry year \"([^\"]*)\"$")
	public void iEnterTheMedicareCardExpiryYear(String year) {
		medicareTabPage.enterMedicareCardExpiryYear(year);
	}

	// Step to click on the Compliance tab
	@When("I click on compliance tab")
	public void iClickAtComplianceTab() {
		complianceTab.clickComplianceTab();
	}

	// Step to select the Hospital-extra Waiting Periods checkbox
	@When("I select the Hospital-extra Waiting Periods checkbox")
	public void iSelectTheHospitalExtraWaitingPeriodsCheckbox() {
		complianceTab.selectHospitalExtraWaitingPeriods();
	}

	// Step to select the Exclusions-Restrictions checkbox
	@When("I select the Exclusions-Restrictions checkbox")
	public void iSelectTheExclusionsRestrictionsCheckbox() {
		complianceTab.selectExclusionsRestrictions();
	}

	// Step to select the Rebate checkbox
	@When("I select the Rebate checkbox")
	public void iSelectTheRebateCheckbox() {
		complianceTab.selectRebate();
	}

	// Step to select the LHC checkbox
	@When("I select the LHC checkbox")
	public void iSelectTheLHCCheckbox() {
		complianceTab.selectLHC();
	}

	// Step to select the Excess Rules/Waiting Periods checkbox
	@When("I select the Excess Rules-Waiting Periods checkbox")
	public void iSelectTheExcessRulesWaitingPeriodsCheckbox() {
		complianceTab.selectExcessRulesWaitingPeriods();
	}

	@When("I click on the Affiliation tab")
	public void clickOnAffiliationsTab() {
		affiliationsTab.clickAffiliationsTab();
	}

	@When("I click on the Association")
	public void iClickOnTheAssociation() {
		affiliationsTab.clickAssociation(); // Calling the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the association dropdown and traverses {int}st step")
	public void associationDropdownStep(int stepsToTraverse) throws InterruptedException {
		affiliationsTab.associationDropdownValues(stepsToTraverse); // Invoke the action method
	}

	@When("I click on the Product")
	public void iClickOnTheProductTab() {
		generateQuote.clickProductTab(); // Calling the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the class dropdown and traverses {int}st step")
	public void classDropdownStep(int stepsToTraverse) throws InterruptedException {
		generateQuote.selectOptionFromClassDropdown(stepsToTraverse); // Invoke the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the hospital dropdown and traverses {int}st step")
	public void hospitalDropdownStep(int stepsToTraverse) throws InterruptedException {
		generateQuote.selectOptionFromHospitalDropdown(stepsToTraverse); // Invoke the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the extra dropdown and traverses {int}st step")
	public void extraDropdownStep(int stepsToTraverse) throws InterruptedException {
		generateQuote.selectOptionFromExtraDropdown(stepsToTraverse); // Invoke the action method
	}

	// Click on financial tab
	@When("I click on the Financial")
	public void iClickOnTheFinancialTab() {
		generateQuote.clickFinancialTab(); // Calling the action method
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the payment frequency dropdown and traverses {int}st step")
	public void paymentFrequencyDropdownStep(int stepsToTraverse) throws InterruptedException {
		generateQuote.selectOptionFromPaymentFrequencyDropdown(stepsToTraverse); // Invoke the action method
	}

	// Click on calculate quote button
	@When("I click on the calculate quote button")
	public void iClickOnTheCalculateQuoteButton() {
		generateQuote.clickCalculateQuote(); // Calling the action method
	}

	// Click on save_1 button
	@When("I click on the save_1 button")
	public void iClickOnTheSave_1_Button() {
		generateQuote.clickSave_1_Button(); // Calling the action method
	}

	@Then("I capture the success message and close the popup")
	public void iCaptureTheSuccessMessageAndCloseThePopup() throws InterruptedException {
		Thread.sleep(4000);
		// baseTest.waitForVisibility(generateQuote.successPopup, driver);
		String successMessage = generateQuote.captureSuccessMessageAndClosePopup(); // Call the action method
		System.out.println("Captured Success Message: " + successMessage); // Print or log the message
		Assert.assertEquals(successMessage, "Success: Current Quote Record has been created successfully.");
	}

	// Click on Financial Info Page
	@When("I click on the Financial Info")
	public void iClickOnTheFinancialInfoTab() {
		financialInfoPage.clickFinancialTab();// Calling the action method
	}

	@When("The user clicks on the Group dropdown, enters the {string} into the suggestion input box, and selects the {int} option using the keyboard.")
	public void userHandlesGroupDropdownWithKeyboard(String inputText, int stepsToTraverse)
			throws InterruptedException {
		financialInfoPage.handleGroupDropdownWithKeyboard(inputText, stepsToTraverse, driver);
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	@When("the user click on the debit account type dropdown and traverses {int}st step")
	public void debitAccountTypeDropdownStep(int stepsToTraverse) throws InterruptedException {
		financialInfoPage.selectOptionFromDebitAccountTypeDropdown(stepsToTraverse); // Invoke the action method
	}

	@When("The user enters a random name in the Debit Bank Account Name field")
	public void userEntersRandomNameInDebitBankAccountNameField() {
		financialInfoPage.enterRandomDebitBankAccountName();
	}

	@When("^I enter the valid BSB number \"([^\"]*)\"$")
	public void iEnterTheValidBSBNumber(String bsbNumber) {

		financialInfoPage.enterBSB(bsbNumber);
	}

	@When("The user enters {string} in the Debit Bank Account Number field")
	public void userEntersInDebitBankAccountNumberField(String accountNumber) {
		financialInfoPage.enterDebitBankAccountNumber(accountNumber);
	}

	@When("The user enters a random name in the Benefit Bank Account Name field")
	public void userEntersRandomNameInBenefitBankAccountNameField() {
		financialInfoPage.enterRandomBenefitBankAccountName();
	}

	@When("^I enter the valid benefit BSB number \"([^\"]*)\"$")
	public void iEnterTheValidBenefitBSBNumber(String bsbNumber) {

		financialInfoPage.enterBSBInBenefitInputField(bsbNumber);
	}

	@When("The user enters {string} in the Benefit Bank Account Number field")
	public void userEntersInBenefitBankAccountNumberField(String accountNumber) {
		financialInfoPage.enterBenefitBankAccountNumber(accountNumber);
	}

	// Click on Wrap Up Page
	@When("I click on the Wrap Up Tab")
	public void iClickOnTheWrapUpTab() {
		wrapUpPage.clickWrapUpTab();// Calling the action method
	}
	
	//Join date
	@When("The user enters joining date")
	public void userEntersTodaysJoinDate() {
	    wrapUpPage.enterJoinDate();
	}
	
	@When("the user click on the welcome call dropdown and traverses {int}st step")
	public void welcomeCallDropdownStep(int stepsToTraverse) throws InterruptedException {
		wrapUpPage.selectOptionFromWelcomeCallDropdown(stepsToTraverse); // Invoke the action method
	}
	
	@When("The user selects the Claiming Process checkbox")
	public void userSelectsClaimingProcessCheckbox() {
	    wrapUpPage.selectClaimingProcessCheckbox();
	}
	
	@When("The user selects the OMS Registration checkbox")
	public void userSelectsOMSRegistrationCheckbox() {
	    wrapUpPage.selectOMSRegistrationCheckbox();
	}
	
	@When("the user click on the authority level dropdown and traverses {int}nd step")
	public void authorityLevelDropdownStep(int stepsToTraverse) throws InterruptedException {
		wrapUpPage.selectOptionAuthorityLevelCallDropdown(stepsToTraverse); // Invoke the action method
	}
	
	@When("The user clicks on the Convert to Membership button")
	public void userClicksConvertToMembershipButton() throws InterruptedException {
		Thread.sleep(8000);
	    wrapUpPage.clickConvertToMembershipButton();
	}
	
	@Then("I capture the membership creation message and validate the ID")
	public void iCaptureMembershipCreationMessageAndValidateId() throws InterruptedException {
		Thread.sleep(9000);
	    wrapUpPage.captureMembershipCreationMessage();
	    Assert.assertNotNull(WrapUpTabPage.membershipId, "Membership ID was not extracted correctly.");
	    Assert.assertFalse(WrapUpTabPage.membershipId.isEmpty(), "Membership ID is empty.");
	    System.out.println("Membership has been created successfully with ID: " + WrapUpTabPage.membershipId);
	}

}
