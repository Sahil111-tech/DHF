package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features/QuickQuote.feature", glue = { "StepDefinitions" }, plugin = { "pretty",
		"html:reports/TestReports.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, // This is the plugins in cucumber to integrates Extent Reports to generate detailed reports.
dryRun = false, 
monochrome = true,
publish=true,
tags = "@starterExtras" 
)


public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
