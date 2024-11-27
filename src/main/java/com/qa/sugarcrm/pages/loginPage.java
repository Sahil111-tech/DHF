package com.qa.sugarcrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

   
	
	
	
	

	/** Respectives Actions Methods **/
	public void clickShowLogInForm() {
		waitForVisibility(showLogInFormLink, driver);
		waitForElementToBeClickable(driver, showLogInFormLink);
		showLogInFormLink.click();
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
    }

	// Method to get the page title
	public String getPageTitle() {
		return driver.getTitle(); // Returns the title of the current page
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl(); // This should return the URL of the current page
	}
	

}