package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features/WebsiteSmoke.feature", glue = { "StepDefinitions" }, plugin = {
		"pretty", "html:reports/TestReports.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, // This is the plugins in cucumber to
																					// integrates Extent Reports to
																					// generate detailed reports.
		dryRun = false, //
		monochrome = true, // Makes the console output more readable by removing unnecessary characters and
							// formatting.
		publish = true // Publishes the generated Cucumber report to a sharable URL.
		//tags = /*"@CompleteMAFSubmission or @starterExtras"*/ "@CompleteMAFSubmission" 
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

}