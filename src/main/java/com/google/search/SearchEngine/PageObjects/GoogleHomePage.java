package com.google.search.SearchEngine.PageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.search.SearchEngine.Base.BasePage;

public class GoogleHomePage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(GoogleHomePage.class);

	By searchTextBox = By.name("q");
	By googleSearchButton = By.name("btnK");

	public GoogleHomePage(WebDriver driver) {
		super(driver);
	}

	public void enterSearchKeyword(String keyword) {
		sendkeys(searchTextBox, keyword);
		logger.info("Serach keyword entered.");
	}

	public void clickGoogleSearchButton() throws IOException, InterruptedException {
		timeinterval(1);
		click(googleSearchButton);
		logger.info("Clicked on search button.");
	}

}
