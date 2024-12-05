package com.qa.sugarcrm.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;

public class WrapUpTabPage extends BaseTest {

	protected WebDriver driver;
	// Class-level static variable to store the membership ID
	public static String membershipId;

	public WrapUpTabPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locate Benefit bank account number field
	@FindBy(xpath = "//span[text()='Wrap Up']")
	private WebElement wrapUpTab;

	// Locate join date input box
	@FindBy(xpath = "//input[@name='join_date_c']")
	private WebElement joinDateInput;

	// Locate join welcome call dropdown
	@FindBy(xpath = "//span[@data-fieldname='welcome_call_c']")
	private WebElement welcomeCallDropdown;

	// FindBy for Claiming Process checkbox
	@FindBy(xpath = "//input[@aria-label='Claiming Process']")
	private WebElement claimingProcessCheckbox;

	// FindBy for OMS Registration checkbox
	@FindBy(xpath = "//input[@aria-label='OMS Registration']")
	private WebElement omsRegistrationCheckbox;

	// Locate join authority level dropdown
	@FindBy(xpath = "//span[@data-fieldname='authority_level_c']")
	private WebElement authorityLevelDropdown;

	// Locate convert to Membership button
	@FindBy(xpath = "//a[@name='convert_to_membership']")
	private WebElement convertToMembershipButton;

	@FindBy(xpath = "(//div[@id='alerts']//div/div)[1]")
	private WebElement membershipCreatedSuccessPopup;
	
	@FindBy(xpath = "//button[@data-action='close']")
	private WebElement closePopupButton;

	/***************************
	 * Actions Method
	 *********************************************************/

	// Method to click on the Financial tab
	public void clickWrapUpTab() {
		waitForElementToBeClickable(driver, wrapUpTab);
		wrapUpTab.click();
	}

	// Action method to enter join date
	public void enterJoinDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		String formattedDate = today.format(formatter);

		try {
			LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);

			// Enter the date into the input field
			waitVisibilityElement(joinDateInput);
			joinDateInput.clear();
			joinDateInput.sendKeys(formattedDate);

			// Press Enter to close the calendar
			joinDateInput.sendKeys(Keys.ENTER);

			System.out.println("Entered join date: " + formattedDate);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(
					"Error formatting today's date. Please ensure correct format: dd/MM/yyyy");
		}
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	public void selectOptionFromWelcomeCallDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(welcomeCallDropdown);
		welcomeCallDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Action method to select the Claiming Process checkbox
	public void selectClaimingProcessCheckbox() {
		waitVisibilityElement(claimingProcessCheckbox);
		if (!claimingProcessCheckbox.isSelected()) {
			claimingProcessCheckbox.click();
		}
		System.out.println("Claiming Process checkbox selected.");
	}

	// Action method to select the OMS Registration checkbox
	public void selectOMSRegistrationCheckbox() {
		waitVisibilityElement(omsRegistrationCheckbox);
		if (!omsRegistrationCheckbox.isSelected()) {
			omsRegistrationCheckbox.click();
		}
		System.out.println("OMS Registration checkbox selected.");
	}

	// Selects the dropdown option based on the input value: 1 for the first option,
	// 2 for the second, and so on
	public void selectOptionAuthorityLevelCallDropdown(int stepsToTraverse) throws InterruptedException {
		// Wait for the input box to be visible
		waitVisibilityElement(authorityLevelDropdown);
		authorityLevelDropdown.click();
		Actions actions = new Actions(driver);
		for (int i = 0; i < stepsToTraverse; i++) {
			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
			BaseTest.sleepFor(700);
		}
		actions.sendKeys(Keys.ENTER).build().perform();
		// hamburgerMenu.click();

	}

	// Action method to click on the Convert to Membership button
	public void clickConvertToMembershipButton() {
		waitVisibilityElement(convertToMembershipButton);
		waitForElementToBeClickable(driver, convertToMembershipButton);
		convertToMembershipButton.click();
		System.out.println("Clicked on the Convert to Membership button.");
	}

	public void captureMembershipCreationMessage() {
		// Wait for the success popup to be visible

		waitForVisibility(membershipCreatedSuccessPopup, driver);

		// Get the popup message text
		String popupMessage = membershipCreatedSuccessPopup.getText();
		System.out.println("Captured Popup Message: " + popupMessage);

		// Extract the unique membership ID
		String[] parts = popupMessage.split("ID:");
		if (parts.length > 1) {
			membershipId = parts[1].trim();
			System.out.println("Extracted Membership ID: " + membershipId);
		} else {
			throw new RuntimeException("Membership ID not found in the popup message.");
		}
	}
	
	public void clickCloseButton() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Wait for the close button to be clickable
	    WebElement closeElement = wait.until(ExpectedConditions.elementToBeClickable(closePopupButton));

	    // Click the close button
	    closeElement.click();

	    // Log the action for debugging or tracking
	    log.info("Clicked on the Close Button successfully.");
	}

}
