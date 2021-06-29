package com.google.search.SearchEngine.Base;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BasePage extends WebPageGenerator {
	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

	public static int Seconds = 15;
	public ExtentTest test;
	// WebPageGenerator page;

	static ThreadLocal<WebDriver> driverinstance = new ThreadLocal<>();

	public BasePage(WebDriver driver) {
		super(driver);
	}

	public void click(By by) throws IOException {

		try {
			timeinterval(2);
			getHighlightElement(driver.findElement(by));
			driver.findElement(by).click();
			timeinterval(5);
		} catch (NoSuchElementException e) {
			driver.findElement(by).click();
		} catch (Exception e) {
			logger.error("Fail to click on link : " + by + " on page : " + e.getMessage());
			Assert.assertTrue(false, "Fail to click on link : " + by + " on page : " + e.getMessage());
		}
	}

	public void timeinterval(int i) throws InterruptedException {
		Thread.sleep(1000 * i);
	}

	public void sendkeys(By by, String keys) {
		getHighlightElement(driver.findElement(by));
		driver.findElement(by).sendKeys(keys);
	}

	public void getHighlightElement(final WebElement element) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, Seconds);
			// Wait for search to complete
			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver webDriver) {
					return element != null;
				}
			});
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", element);
		} catch (Exception e) {
			logger.info("Fail to highlight the Element");
		}
	}

	public String getText(By by) {
		getHighlightElement(driver.findElement(by));
		return driver.findElement(by).getText();
	}
}
