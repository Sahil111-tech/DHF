package com.qa.sugarcrm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class AffiliationsTabPage extends BaseTest {
	protected WebDriver driver;

	// constructor for initialization page factory and drivers
	public AffiliationsTabPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for affiliation button
	@FindBy(xpath = "//span[text()='Affiliations']")
	public static WebElement affiliationsTab;
	
	// Locator for Association
	@FindBy(xpath = "//span[text()='Association']")
	public WebElement association;
	
	@FindBy(xpath="//span[@data-fieldname='association_c']")
	WebElement associationDropdown;

	/***************************
	 * Actions Method
	 *********************************************************/
	// Method to click on the affiliation button
	public void clickAffiliationsTab() {
		waitForElementToBeClickable(driver, affiliationsTab);
		affiliationsTab.click();
	}
	
	// Action method to click on Association
	public void clickAssociation() {
	    waitForVisibility(association, driver); // Wait for the element to be visible
	    waitForElementToBeClickable(driver, association); 
	    association.click(); // Perform the click action
	}
	
	//Selects the dropdown option based on the input value: 1 for the first option, 2 for the second, and so on
  	public void associationDropdownValues(int stepsToTraverse) throws InterruptedException {
  		// Wait for the input box to be visible
  		waitVisibilityElement(associationDropdown);
  		associationDropdown.click();
  		Actions actions = new Actions(driver);
  		for (int i = 0; i < stepsToTraverse; i++) {
  			actions.sendKeys(Keys.ARROW_DOWN).perform(); // Press the down arrow key
  			BaseTest.sleepFor(700);
  		}
  		actions.sendKeys(Keys.ENTER).build().perform();
  		// hamburgerMenu.click();

  	}
}
