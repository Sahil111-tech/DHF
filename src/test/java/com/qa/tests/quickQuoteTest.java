package com.qa.tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.airteam.pages.quickQuote;

public class quickQuoteTest extends BaseTest {

	private quickQuote quote;

	@Test(priority = 1)
	public void testQuickQuoteProcess() throws InterruptedException {
		quote = new quickQuote();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// Step 1: Click on "Get Quote" btn
		// Verify the visibility of the "Get Quote" button
        Assert.assertTrue(quote.isGetQuoteButtonVisible(), "Get Quote button is not visible.");
        quote.clickGetQuote();

		// Step 2: Select cover for single
		quote.selectCoverForSingle();

		// Step 3: Click on "Next" button
		quote.clickNext();

		// Step 4: Select residence "NSW"
		quote.clickResidenceNSW();

		// Step 5: Click on Next button
		quote.clickNext();

		// Step 6: Enter date of birth
		quote.enterDateOfBirth("01");
		quote.enterMonthOfBirth("01");
		quote.enterYearOfBirth("1990");

		// Step 7: Click on Next button
		quote.clickNext();

		// Step 8: Select annual income
		quote.selectAnnualIncomeOption();

		// Step 9: Click on Calculate cover button
		quote.clickCalculateCover();
		
		// Verify page title after the quote process
        String expectedTitle = "Get a private health insurance quote | Doctors' Health Fund"; // Change this to the expected title
        String actualTitle = quote.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        
     

	}

}
