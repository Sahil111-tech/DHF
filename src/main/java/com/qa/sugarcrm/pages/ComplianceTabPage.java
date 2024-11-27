package com.qa.sugarcrm.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseTest;

public class ComplianceTabPage extends BaseTest {
	protected WebDriver driver;

	// constructor for initialization page factory and drivers
	public ComplianceTabPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locator for Medicare button
	@FindBy(xpath = "//span[text()='Compliance']")
	public static WebElement complianceTab;

	// Locator for Hospital/extra Waiting Periods checkbox
	@FindBy(xpath = "//input[@aria-label='Hospital/extra Waiting Periods']")
	private WebElement hospitalExtraWaitingPeriodsCheckBox;

	// Locator for Exclusions/Restrictions checkbox
	@FindBy(xpath = "//input[@aria-label='Exclusions/Restrictions']")
	private WebElement exclusionsRestrictionsCheckBox;

	// Locator for Rebate checkbox
	@FindBy(xpath = "//input[@aria-label='Rebate']")
	private WebElement rebateCheckBox;

	// Locator for LHC checkbox
	@FindBy(xpath = "//input[@aria-label='LHC']")
	private WebElement lhcCheckBox;

	// Locator for Excess Rules/Waiting Periods checkbox
	@FindBy(xpath = "//input[@aria-label='Excess Rules/Waiting Periods']")
	private WebElement excessRulesWaitingPeriodsCheckBox;

	/***************************
	 * Actions Method
	 *********************************************************/
	// Method to click on the Medicare button
	public void clickComplianceTab() {
		waitForElementToBeClickable(driver, complianceTab);
		complianceTab.click();
	}

	// Action Method to select a Hospital/extra Waiting Periods checkbox
	public void selectHospitalExtraWaitingPeriods() {
		if (!hospitalExtraWaitingPeriodsCheckBox.isSelected()) {
			hospitalExtraWaitingPeriodsCheckBox.click();
		}
	}

	// Action Method to select a Exclusions/Restrictions checkbox
	public void selectExclusionsRestrictions() {
		if (!exclusionsRestrictionsCheckBox.isSelected()) {
			exclusionsRestrictionsCheckBox.click();
		}
	}

	// Action Method to select a Rebate checkbox
	public void selectRebate() {
		if (!rebateCheckBox.isSelected()) {
			rebateCheckBox.click();
		}
	}

	// Action Method to select a LHC checkbox
	public void selectLHC() {
		if (!lhcCheckBox.isSelected()) {
			lhcCheckBox.click();
		}
	}

	// Action Method to select a ExcessRulesWaitingPeriods checkbox
	public void selectExcessRulesWaitingPeriods() {
		if (!excessRulesWaitingPeriodsCheckBox.isSelected()) {
			excessRulesWaitingPeriodsCheckBox.click();
		}
	}

}
