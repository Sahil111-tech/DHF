package runners;

import org.testng.annotations.Listeners;

//import com.qa.listeners.CucumberCustomReportGenerator;
//import com.qa.listeners.ExtentReportListener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/Features", 
glue = { "StepDefinitions" }, 
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }
//tags = "@Smoke" // Specify tags if needed
)

//@Listeners({ExtentReportListener.class, CucumberCustomReportGenerator.class}) // Register both listeners
public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
