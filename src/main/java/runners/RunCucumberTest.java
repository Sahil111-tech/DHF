package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/Features", // Path to our feature files
	    glue = {"StepDefinitions"}, // Package where our step definitions are located
	    plugin = {"pretty", "html:target/cucumber-reports.html"} // Our Reports
	)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
