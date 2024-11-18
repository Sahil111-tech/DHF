package com.qa.airteam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.BaseTest;

public class AdditionalInformationPage extends BaseTest{
	// constructor for initialization page factory and drivers
		public AdditionalInformationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		/***** Covering here Additional Information *****/
		// To select Yes: Do you wish to nominate a person other than yourself as an authorised person on the policy?
		@FindBy(xpath = "//div[@name='isNominatePerson']/div/label[1]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
		private WebElement isNominatedRadioButtonYes;

		// To select No: Do you wish to nominate a person other than yourself as an authorised person on the policy?

		@FindBy(xpath = "//div[@name='isNominatePerson']/div/label[2]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
		private WebElement isNominatedRadioButtonNo;
		
		// Radio buttons to select "Yes": Are you currently a member of Avant?
		@FindBy(xpath = "//div[@name='isAvantGroupMember']/div/label[1]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
		private WebElement yesAvantGroupMemberRadioButton;
		// Radio buttons to select "No": Are you currently a member of Avant?
		@FindBy(xpath = "//div[@name='isAvantGroupMember']/div/label[2]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
		private WebElement noAvantGroupMemberRadioButton;
		
		// Dropdown for 'howDidYouHearAboutUs'
		@FindBy(xpath = "//select[@name='howDidYouHearAboutUs']")
		private WebElement howDidYouHearAboutUsDropdown;
		
		// Radio button for Yes (Would you like us to check in with you after you join?)
		@FindBy(xpath = "//div[@name='getInTouch']/div/label[1]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='Yes']")
		private WebElement yesCheckInRadioButton;

		// Radio button for No (Would you like us to check in with you after you join?)
		@FindBy(xpath = "//div[@name='getInTouch']/div/label[2]/span/span[@class='ant-radio-inner']/ancestor::label/span[text()='No']")
		private WebElement noCheckInRadioButton;
		
		// Checkbox for "I have read and agree to the Terms and Conditions of Service."
		@FindBy(xpath = "//input[@type='checkbox' and @data-id='acceptTermsAndConditions']/..")
		private WebElement termsAndConditionsCheckbox;
		
		// Checkbox for "I agree to The Doctors' Health Fund using my personal
		// information in accordance with its Privacy Policy providing information and
		// marketing material by phone, text message, mail or email."
		@FindBy(xpath = "//label[@for='agreeUsingPersonalInformation']")
		private WebElement agreeUsingPersonalInformationCheckbox;
		
		// Checkbox for "I have read and agree to the AHSA Privacy Policy."
		@FindBy(xpath = "//input[@type='checkbox' and @data-id='acceptAHSA']/..")
		private WebElement ahsaPrivacyPolicyCheckbox;
		
		// Checkbox for "The information entered in this application is true and correct
		// and I confirm I am eligible to join Doctors' Health Fund."
		@FindBy(xpath = "//label[@for='confirmApplicationIsTrue']")
		private WebElement confirmApplicationIsTrueCheckbox;
		
		// Locator for continue button
		@FindBy(xpath = "//div[text()='Continue']/../../..//button/..") // "//div[@class='css-159nnwp']//div[@class='css-1p15x7ze18zzrqo1']"
		protected WebElement clickContinueButton;
		
		
		/*******************************
		 * Actions Methods
		 *********************************/
// Action method to select "Yes" or "No" based on input for question : Do you wish to nominate a person other than yourself as an authorised person on the policy?
     public void selectRadioButton(String choice) {
			if (choice.equalsIgnoreCase("Yes")) {
				isNominatedRadioButtonYes.click();
			} else if (choice.equalsIgnoreCase("No")) {
				isNominatedRadioButtonNo.click();
			} else {
				throw new IllegalArgumentException("Invalid choice. Please select 'Yes' or 'No'.");
			}
		}
		
		// Action method to select 'Yes' or 'No' based on the input: Are you currently a member of Avant?
		public void selectAvantGroupMemberRadioButton(String option) {
			if (option.equalsIgnoreCase("Yes")) {
				yesAvantGroupMemberRadioButton.click();
			} else if (option.equalsIgnoreCase("No")) {
				noAvantGroupMemberRadioButton.click();
			} else {
				throw new IllegalArgumentException("Invalid option. Please select either 'Yes' or 'No'");
			}

		}
		
		// Action method to select a value from the dropdown: How did you hear about us?
		public void selectHowDidYouHearAboutUsOption(String option) {
			Select select = new Select(howDidYouHearAboutUsDropdown);
			// Checks if the dropdown contains the option by comparing each option's visible text with the provided 'option' value.
			if (select.getOptions().stream().anyMatch(o -> o.getText().equals(option))) {
				select.selectByVisibleText(option);
			} else {
				throw new IllegalArgumentException("The provided option is not available in the dropdown: " + option);
			}
		}
		
		/*
		 * Action method to select Yes or No based on input for "Would you like us to
		 * check in with you after you join?"
		 */		public void selectCheckInRadioButton(String option) {
			if (option.equalsIgnoreCase("Yes")) {
				yesCheckInRadioButton.click();
			} else if (option.equalsIgnoreCase("No")) {
				noCheckInRadioButton.click();
			} else {
				throw new IllegalArgumentException("Invalid option provided. Please select 'Yes' or 'No'.");
			}
		}
		
		// Action method to select or deselect the "I have read and agree to the Terms
		// and Conditions of Service."
		public void selectTermsAndConditionsCheckbox(boolean select) {
			if (select && !termsAndConditionsCheckbox.isSelected()) {
				termsAndConditionsCheckbox.click();
			} else if (!select && termsAndConditionsCheckbox.isSelected()) {
				termsAndConditionsCheckbox.click();
			}
		}
		
		// Action method to select or deselect "I agree to The Doctors' Health Fund
		// using my personal information in accordance with its Privacy Policy."
		public void selectAgreeUsingPersonalInformationCheckbox(boolean select) {
			if (select && !agreeUsingPersonalInformationCheckbox.isSelected()) {
				agreeUsingPersonalInformationCheckbox.click();
			} else if (!select && agreeUsingPersonalInformationCheckbox.isSelected()) {
				agreeUsingPersonalInformationCheckbox.click();
			}
		}
		
		// Action method to select or deselect "I have read and agree to the AHSA
		// Privacy Policy."
		public void selectAhsaPrivacyPolicyCheckbox(boolean select) {
			if (select && !ahsaPrivacyPolicyCheckbox.isSelected()) {
				ahsaPrivacyPolicyCheckbox.click();
			} else if (!select && ahsaPrivacyPolicyCheckbox.isSelected()) {
				ahsaPrivacyPolicyCheckbox.click();
			}
		}
		
		// Action method to select or deselect "The information entered in this
		// application is true and correct and I confirm I am eligible to join Doctors'
		// Health Fund."
		public void selectConfirmApplicationIsTrueCheckbox(boolean select) {
			if (select && !confirmApplicationIsTrueCheckbox.isSelected()) {
				confirmApplicationIsTrueCheckbox.click();
			} else if (!select && confirmApplicationIsTrueCheckbox.isSelected()) {
				confirmApplicationIsTrueCheckbox.click();
			}
		}
		
		// Action method to click "Continue radio button"
		public WebElement clickContinueButton() throws InterruptedException {
			// waitVisibility(clickContinueRadioButton);
			waitForVisibility(clickContinueButton, driver);
			waitForElementToBeClickable(driver, clickContinueButton);
			Thread.sleep(2000);
			click(clickContinueButton);
			log.info("click on continue button");
			// Thread.sleep(2000);
			return clickContinueButton;
		}
}
