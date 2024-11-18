package com.qa.sugarcrm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.BaseTest;

public class LeadsPage extends BaseTest {
	protected WebDriver driver;

	// constructor for initialization page factory and drivers
	public LeadsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='Leads_sidebar-nav-item']/a")
	private WebElement leadsButton;

	@FindBy(xpath = "//a[@name='create_button']")
	private WebElement createButton;

	@FindBy(xpath = "//input[@class='select2']/ancestor::span[@data-fieldname='salutation']")
	private WebElement salutationDropdown;

	@FindBy(xpath = "//input[@id='s2id_autogen4_search']")
	private WebElement salutationInputField;

	@FindBy(xpath = "//ul[@id='select2-results-4']//li")
	private List<WebElement> salutationOptions;

	/*** Actions Method ****/

	// Action method to click on the Leads button
	public void clickLeadsButton() {
		waitForVisibility(leadsButton, driver);
		leadsButton.click();
		log.info("Clicked succesfully on the Leads button.");
	}

	// Action method to click on the Create button
	public void clickCreateButton() {
		waitForVisibility(createButton, driver);
		createButton.click();
		log.info("Clicked succesfully on the Create button.");
	}

	// Action method to handle Salutation dropdown
	public void selectSalutation(String salutation) {
		boolean found = false;

		// Step 1: Click the dropdown to open it
		salutationDropdown.click();
		log.info("Dropdown opened.");

		// Step 2: Type the salutation value (e.g. "Mr") into the search box
		salutationInputField.sendKeys(salutation);
		log.info("Searched for salutation: " + salutation);

		// Wait for the options to load
		waitForVisibility(salutationOptions, driver);

		// Step 3: Iterate through the options and click the matching one
		for (WebElement option : salutationOptions) {
			String optionText = option.getText().trim(); // Get the text of each option
			log.info("Checking option: " + optionText);

			// Match the salutation value dynamically
			if (optionText.equalsIgnoreCase(salutation)) {
				option.click(); // Click on the matched option
				log.info("Selected salutation: " + salutation);
				found = true;
				break; // Exit the loop once the option is found and clicked
			}
		}

		// If not found
		if (!found) {
			log.warn("Salutation '" + salutation + "' not found in the dropdown.");
		}
	}
}
