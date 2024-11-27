package com.qa.sugarcrm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class GenerateQuotePage extends BaseTest {
	protected WebDriver driver;

	public GenerateQuotePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for Product Tab
	@FindBy(xpath = "//a[text()='Product']")
	public static WebElement productTab;

	// Locator for class dropdown
	@FindBy(xpath = "//select[@id='class_dropdown']")
	public static WebElement classDropdown;

	// Locator for hospital dropdown
	@FindBy(xpath = "//select[@id='hospital_dropdown']")
	public static WebElement hospitalDropdown;

	// Locator for extra dropdown
	@FindBy(xpath = "//select[@id='extra_dropdown']")
	public static WebElement extraDropdown;

	// Locator for financial tab
	@FindBy(xpath = "//a[text()='Financial']")
	public static WebElement financialTab;

	// Locator for Payment frequency dropdown
	@FindBy(xpath = "//select[@id='payment_frequency']")
	public static WebElement paymentFrequencyDropdown;

	// Locator for calculate Quote Button
	@FindBy(xpath = "//input[@id='calculate_quote']")
	public static WebElement calculateQuoteButton;

	// Locator for save Button
	@FindBy(xpath = "//input[@id='save_1']")
	public static WebElement save_1_Button;

	// Locators
	@FindBy(xpath = "//div[@id='alerts']//div/div[1]")
	public WebElement successPopup;

	@FindBy(xpath = "//button[@data-action='close']")
	public WebElement closePopupButton;

	/***************************
	 * Actions Method
	 *********************************************************/

	// Method to click on the Product tab
	public void clickProductTab() {
		waitForElementToBeClickable(driver, productTab);
		productTab.click();
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	public void selectOptionFromClassDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(classDropdown);
		classDropdown.click();
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
	public void selectOptionFromHospitalDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(hospitalDropdown);
		hospitalDropdown.click();
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
	public void selectOptionFromExtraDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(extraDropdown);
		extraDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Method to click on the Financial tab
	public void clickFinancialTab() {
		waitForElementToBeClickable(driver, financialTab);
		financialTab.click();
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	public void selectOptionFromPaymentFrequencyDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(paymentFrequencyDropdown);
		paymentFrequencyDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Method to click on the CalculateQuote
	public void clickCalculateQuote() {
		waitForElementToBeClickable(driver, calculateQuoteButton);
		calculateQuoteButton.click();
	}

	// Method to click on the Save_1 Button
	public void clickSave_1_Button() {
		waitForElementToBeClickable(driver, save_1_Button);
		save_1_Button.click();
	}

	// Method to capture success message and close the popup
	public String captureSuccessMessageAndClosePopup() {
		waitForVisibility(successPopup, driver); // Wait for the popup to be visible
		String successMessage = successPopup.getText(); // Capture the success message text
		closePopupButton.click(); // Close the popup by clicking the close button
		return successMessage; 
	}

}
