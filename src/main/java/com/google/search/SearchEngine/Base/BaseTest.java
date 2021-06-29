package com.google.search.SearchEngine.Base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.google.search.SearchEngine.Utilities.ExtentReportGenerator;
import com.google.searchEngine.Configuration.WebConfiguration;

public class BaseTest {

	ExtentReportGenerator extentreportgenerator;
	DriverFactory driverfactory;
	public WebPageGenerator webPageGenerator;
	public ExtentTest test;

	public BaseTest() {
		extentreportgenerator = new ExtentReportGenerator();
	}

	@BeforeSuite
	public void beforeSuite() {

		extentreportgenerator.startExtentReport();
		PropertyConfigurator.configure("log4j.properties");

	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {

		DriverFactory.build(WebConfiguration.getDriverEnvironment(),
				DriverFactory.setup(WebConfiguration.getDriverEnvironment()));
		webPageGenerator = new WebPageGenerator(DriverFactory.driver);
		DriverFactory.driver.get(WebConfiguration.getRemoteURL());

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test = extentreportgenerator.createExtentReport(method);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		extentreportgenerator.captureTestResult(result);
	}

	@AfterTest
	public void afterTest() {

	}

	@AfterSuite
	public void afterSuite() {

		extentreportgenerator.endExtentReport();
		DriverFactory.DestroyDriver();
	}
}
