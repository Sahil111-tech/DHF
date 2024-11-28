package com.qa.sugarcrm.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;

public class loginPage extends BaseTest {
	protected WebDriver driver;

	// constructor for initialization page factory and drivers
	public loginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for the Show log in form link
	@FindBy(xpath = "//a[text()='Show log in form']")
	private WebElement showLogInFormLink;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//a[@name='login_button']")
	private WebElement loginButton;

	@FindBy(xpath = "//strong[text()='Success:']/../..")
	private WebElement successPopup;

	@FindBy(xpath = "//strong[text()='Success:']/../preceding-sibling::button")
	private WebElement closeSuccessPopupButton;

	@FindBy(xpath = "//strong[text()='Warning:']/../..")
	private WebElement warningPopup;

	@FindBy(xpath = "//strong[text()='Warning:']/../preceding-sibling::button")
	private WebElement closeWarningPopupButton;

	/** Respectives Actions Methods **/
	public void clickShowLogInForm() {
		try {
			// Wait for the element to be visible
			waitForVisibility(showLogInFormLink, driver);

			// Wait for the element to be clickable
			waitForElementToBeClickable(driver, showLogInFormLink);

			// Click the element
			showLogInFormLink.click();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is stale. Refreshing the reference...");

			// Refresh the reference to the element
			showLogInFormLink = driver.findElement(By.xpath("//a[text()='Show log in form']"));

			// Retry the click operation
			waitForVisibility(showLogInFormLink, driver);
			waitForElementToBeClickable(driver, showLogInFormLink);
			showLogInFormLink.click();
		}
	}

	public void enterUsername(String username) {
		waitForVisibility(usernameField, driver);
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		waitForVisibility(passwordField, driver);
		passwordField.clear();
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		waitForVisibility(loginButton, driver);
		loginButton.click();

		// Temporarily set implicit wait to zero
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Explicit wait for the popup
			wait.until(ExpectedConditions.elementToBeClickable(closeSuccessPopupButton)); // Check if clickable
			closeSuccessPopupButton.click(); // Try to close the popup
			System.out.println("Closed the success popup.");
		} catch (TimeoutException e) {
			System.out.println("Success popup is not displayed.");
		} finally { // Restore original implicit wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}

		/*
		 * try { closeSuccessPopupButton.click(); // Attempt to close directly
		 * System.out.println("Closed the success popup."); } catch
		 * (NoSuchElementException e) {
		 * System.out.println("Success popup is not displayed."); }
		 */

		/*
		 * boolean successPop =
		 * isElementPresent(By.xpath("//strong[text()='Success:']/../.."), driver);
		 * //boolean
		 * warningPop=isElementPresent(By.xpath("//strong[text()='Warning:']/../.."),
		 * driver); System.out.println("Is Success Popup Present? " + successPop);
		 * //System.out.println("Is Warning Popup Present? "+warningPop); // If the
		 * popup is displayed, click the close button if (successPop) { try {
		 * //waitForVisibility(closeSuccessPopupButton, driver);
		 * closeSuccessPopupButton.click();
		 * System.out.println("Closed the success popup."); } catch (Exception e) {
		 * System.out.println("Failed to close the success popup: " + e.getMessage()); }
		 * } else { System.out.println("Success popup is not displayed"); }
		 */

		/*
		 * // Handle Warning Popup if (warningPop) { try {
		 * waitForVisibility(closeWarningPopupButton, driver);
		 * closeWarningPopupButton.click();
		 * System.out.println("Closed the warning popup."); } catch (Exception e) {
		 * System.err.println("Failed to close the warning popup: " + e.getMessage()); }
		 * } else { System.out.println("Warning popup is not displayed."); }
		 */
	}

	// System.out.println(successPopup.isDisplayed());

	/*
	 * Boolean popupDisplayed=verifyObjectIsDisplayed(closeSuccessPopupButton);
	 * 
	 * 
	 * if (popupDisplayed.equals(true)) { closeSuccessPopupButton.click();
	 * 
	 * } else { log.info("Success popup is not displayed."); }
	 */
	// Handle popups after clicking the login button
	/*
	 * try { // Check for Success popup log.info("Checking success popup"); if
	 * (isPopupVisible(successPopup, driver, 5)) { closeSuccessPopupButton.click();
	 * log.info("Closed Success popup."); } else {
	 * log.info("No Success popup present."); }
	 * 
	 * // Check for Warning popup if (isPopupVisible(warningPopup, driver, 5)) {
	 * closeWarningPopupButton.click(); log.info("Closed Warning popup."); } else {
	 * log.info("No Warning popup present."); } } catch (Exception e) {
	 * log.warn("No popups to handle or an error occurred while handling popups: " +
	 * e.getMessage()); }
	 */

	// Method to get the page title
	public String getPageTitle() {
		return driver.getTitle(); // Returns the title of the current page
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl(); // This should return the URL of the current page
	}

	// Method to handle popups
	public void closePopupsIfVisible() {
		log.info("Attempting to close any visible popups...");
		try {
			// Handle Success popup
			if (isPopupVisible(successPopup, driver, 5)) {
				closeSuccessPopupButton.click();
				log.info("Closed Success popup.");
			} else {
				log.info("No Success popup found.");
			}

			// Handle Warning popup
			if (isPopupVisible(warningPopup, driver, 5)) {
				closeWarningPopupButton.click();
				log.info("Closed Warning popup.");
			} else {
				log.info("No Warning popup found.");
			}
		} catch (Exception e) {
			log.error("Error while handling popups: ", e);
		}
	}
}