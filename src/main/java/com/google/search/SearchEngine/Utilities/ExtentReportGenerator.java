package com.google.search.SearchEngine.Utilities;

import java.lang.reflect.Method;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportGenerator {

	// builds a new report using the html template
	ExtentHtmlReporter htmlReporter;

	ExtentReports extent;

	// helps to generate the logs in test report.
	public static ExtentTest test;

	public void startExtentReport() {

		htmlReporter = new ExtentHtmlReporter("./test-output/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host Name", "Google Search");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Vivek P.");

		htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
		htmlReporter.config().setReportName("Name of the Report Comes here");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public void captureTestResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		} else {
			test.log(Status.PASS, "Test Case Passed is  " + result.getName());
		}
	}

	public void endExtentReport() {

		extent.flush();
	}

	public ExtentTest createExtentReport(Method method) {

		test = extent.createTest(method.getName());
		test.assignAuthor("Vivek  Parmar");
		test.assignCategory("Regression--PROD");
		test.log(Status.INFO, "Executing Test Case:  " + method.getName());

		return test;

	}
}
