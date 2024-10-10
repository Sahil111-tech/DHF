package runners;

import org.testng.annotations.Listeners;

//import com.qa.listeners.CucumberCustomReportGenerator;
//import com.qa.listeners.ExtentReportListener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = { "StepDefinitions" }, plugin = { "pretty",
		"html:reports/TestReports.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, // This is the plugins in cucumber to integrates Extent Reports to generate detailed reports.
dryRun = false, 
monochrome = true,
publish=true
//tags = "@Smoke" //We can specify tags if needed
)


public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
