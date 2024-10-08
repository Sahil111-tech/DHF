/*
 * package com.qa.listeners;
 * 
 * import com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.reporter.ExtentHtmlReporter; import
 * com.aventstack.extentreports.reporter.configuration.Theme; import
 * org.testng.ITestContext; import org.testng.ITestListener; import
 * org.testng.ITestResult;
 * 
 * public class ExtentReportListener implements ITestListener {
 * 
 * private static ExtentReports extent; private static ExtentTest test;
 * 
 * @Override public void onStart(ITestContext context) { ExtentHtmlReporter
 * htmlReporter = new ExtentHtmlReporter("./reports/extentTestNG.html");
 * htmlReporter.config().setDocumentTitle("Automation Test Report");
 * htmlReporter.config().setReportName("Test Report");
 * htmlReporter.config().setTheme(Theme.STANDARD);
 * 
 * extent = new ExtentReports(); extent.attachReporter(htmlReporter);
 * extent.setSystemInfo("Environment", "QA"); extent.setSystemInfo("Tester",
 * "Sahil Sharma"); }
 * 
 * @Override public void onTestSuccess(ITestResult result) { test =
 * extent.createTest(result.getName()); test.pass("Test Passed"); }
 * 
 * @Override public void onTestFailure(ITestResult result) { test =
 * extent.createTest(result.getName()); test.fail(result.getThrowable()); }
 * 
 * @Override public void onTestSkipped(ITestResult result) { test =
 * extent.createTest(result.getName()); test.skip("Test Skipped"); }
 * 
 * @Override public void onFinish(ITestContext context) { extent.flush(); } }
 */