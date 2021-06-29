package com.google.search.SearchEngine.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.google.search.SearchEngine.Base.BasePage;

public class SearchResultPage extends BasePage {

	By searchResult = By.xpath("//div[@class='uEierd']//div[@role='heading']");

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public void verifySearchResult() {

		Assert.assertTrue(getText(searchResult).startsWith("COVID-19"));
	}

}
