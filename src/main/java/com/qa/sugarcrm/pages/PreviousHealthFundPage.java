package com.qa.sugarcrm.pages;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;

public class PreviousHealthFundPage extends BaseTest {

	protected WebDriver driver;
	// Public static variable to store the random number
    public static String randomHealthFundId;
    // Public static variable to store the generated policy name
    public static String generatedPolicyName;

	// constructor for initialization page factory and drivers
	public PreviousHealthFundPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locate the previous health fund tab
	@FindBy(xpath = "//span[text()='Previous Health Fund']")
	private WebElement previousHealthFund;

	@FindBy(xpath = "//span[@data-fieldname='previous_healthfund_c']")
	private WebElement previousFundButton;

	@FindBy(xpath = "(//div[starts-with(@class, 'select2-drop') and contains(@class, 'select2-with-searchbox')]//input)[44]/following::ul[@class='select2-results']//li")
	private List<WebElement> previousFundOptions;

	// Locate the dropdown to open the Previous Health Fund options
	@FindBy(xpath = "//span[@data-fieldname='previous_fund_transfer_c']")
	private WebElement previousFundTransferDropdown;

	@FindBy(xpath = "//span[@class='h-full w-15']")
	private WebElement hamburgerMenu;

	// Locate the checkbox using @FindBy annotation
	@FindBy(xpath = "//input[@aria-label='Cooling off']")
	private WebElement coolingOffCheckbox;

	// Locate the dropdown to open the since your 31st birthday options
	@FindBy(xpath = "//span[@data-fieldname='since_31st_age_c']")
	private WebElement sinceYour31stBirthdayDropdown;
	
	// Locator for the Health Fund Membership ID input field
    @FindBy(xpath = "//input[@name='previous_healthfund_mem_id_c']")
    private WebElement healthFundMembershipIdInput;
    
 // Locator for the policy name input field
    @FindBy(xpath = "//input[@name='current_phi_c']")
    public static WebElement policyNameInput;
    
    // Locator for the first user fullname in the table
    @FindBy(xpath = "(//h4[text()='My Leads']/following::td[@data-type='fullname'])[1]")
    public static WebElement firstUserFullname;

	/***************************
	 * Action Method
	 **********************************************/

	// Action method to switch to the "Previous Health Fund" tab
	public void switchToPreviousHealthFundTab() {
		waitVisibilityElement(previousHealthFund);
		previousHealthFund.click(); // Click to switch to the tab
		System.out.println("Switched to the Previous Health Fund tab.");
	}

	public void selectPreviousHealthFundValue(int stepsToTraverse) {
		// Wait for the input box to be visible
				waitVisibilityElement(previousFundButton);
				previousFundButton.click();
				Actions actions = new Actions(driver);
				for (int i = 0; i < stepsToTraverse; i++) {
					actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
					BaseTest.sleepFor(700);
				}
				actions.sendKeys(Keys.ENTER).build().perform();
				// hamburgerMenu.click();
	}

	/**
	 * Action method to handle the dropdown and select the "No" option using
	 * keyboard scrolling.
	 */
	public void selectOptionFromPreviousHealthFundDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(previousFundTransferDropdown);
		previousFundTransferDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Action method to check the checkbox
	public void checkCoolingOffCheckbox() {
		waitVisibilityElement(coolingOffCheckbox); // Wait for visibility
		if (!coolingOffCheckbox.isSelected()) { // Check if it is not already selected
			coolingOffCheckbox.click(); // Click to select
			System.out.println("Checkbox 'Cooling off' is now checked.");
		} else {
			System.out.println("Checkbox 'Cooling off' was already checked.");
		}
	}
	
	public void selectOptionFromSinceYour31stBirthdayDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(sinceYour31stBirthdayDropdown);
		sinceYour31stBirthdayDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}
	
	 // Method to generate and enter a random number
    public void enterHealthFundMembershipId() {
        randomHealthFundId = generateRandomNumber(8, 9); // Generates an 8 or 9-digit random number
        healthFundMembershipIdInput.clear();
        healthFundMembershipIdInput.sendKeys(randomHealthFundId);
    }

    // Helper method to generate random numbers of variable length
    private String generateRandomNumber(int minDigits, int maxDigits) {
        int length = new Random().nextBoolean() ? minDigits : maxDigits; // Choose randomly between min and max digits
        int number = (int) (Math.pow(10, length - 1) + new Random().nextDouble() * 9 * Math.pow(10, length - 1));
        return String.valueOf(number);
    }
    
 // Method to generate and enter a meaningful policy name
    public void enterPolicyName() {
        generatedPolicyName = generatePolicyName(); // Generate a meaningful policy name
        policyNameInput.clear();
        policyNameInput.sendKeys(generatedPolicyName);
    }

    // Helper method to generate a meaningful policy name
    private String generatePolicyName() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return "Policy_" + date + "_" + Math.random();
    }
    
 // Method to refresh the page and click on the first user fullname
    public void clickOnFirstUserFullname() {
        BaseTest.refreshPage(driver); // Refresh the page to ensure the table is updated
        waitForVisibility(firstUserFullname, driver); // Wait for the element to appear
        firstUserFullname.click(); // Click on the first user's fullname
    }
    

}
