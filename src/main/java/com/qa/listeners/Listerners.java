package com.qa.listeners;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.BaseTest;

public class Listerners implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		 // Log the failure stack trace if available
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());
        }

        // Instantiate the BaseTest to access the driver and other methods
        BaseTest base = new BaseTest();

        // Capture the screenshot using the method from BaseTest
        File file = base.captureScreenshot();

        // Check if screenshot capture was successful
        if (file != null) {
            // Retrieve parameters from the test context
            Map<String, String> params = new HashMap<>();
            params = result.getTestContext().getCurrentXmlTest().getAllParameters();

            // Construct the image path based on the browser and test result
            String imagePath = "Screenshots" + File.separator +
                    params.get("browser") + File.separator + // Browser name 
                    base.getDateTime() + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + File.separator +
                    result.getName() + ".png";

            // Copy the captured screenshot to the destination path
            try {
                FileUtils.copyFile(file, new File(imagePath));
                System.out.println("Screenshot saved at: " + imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to capture screenshot.");
        }
    }

    // Other ITestListener methods (to be implemented or left as stubs)
    @Override
    public void onTestStart(ITestResult result) {
        // Not needed for now
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Not needed for now
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Not needed for now
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not needed for now
    }

    @Override
    public void onStart(ITestContext context) {
        // Not needed for now
    }

    @Override
    public void onFinish(ITestContext context) {
        // Not needed for now
    }
}