package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/Features", // Path to your feature files
	    glue = {"StepDefinitions"}, // Package where your step definitions are located
	    plugin = {"pretty", "html:target/cucumber-reports.html"} // Reports
	)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
