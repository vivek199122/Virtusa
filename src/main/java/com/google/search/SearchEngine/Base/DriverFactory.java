package com.google.search.SearchEngine.Base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.searchEngine.Configuration.WebConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static WebDriver driver;

	public DriverFactory() {
	}

	public static MutableCapabilities setup(String env) {

		switch (env) {
		case "local":
			return setupLocal();
		case "remote":
			return setupRemote();
		default:
			return null;
		}
	}

	public static WebDriver build(String env, MutableCapabilities caps) throws MalformedURLException {

		switch (env) {
		case "local":
			return buildLocal(caps);
		case "remote":
			return buildRemote(caps);
		default:
			return null;
		}
	}

	public static MutableCapabilities setupLocal() {

		String browser = WebConfiguration.getBrowserName();
		MutableCapabilities caps = null;

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			caps = chromeOptions;
			break;
		case "edge":
			caps = new EdgeOptions();
			break;
		case "firefox":
			caps = new FirefoxOptions();
			break;
		default:
			break;
		}
		return caps;
	}

	public static MutableCapabilities setupRemote() {
		return new DesiredCapabilities();
	}

	public static WebDriver buildLocal(MutableCapabilities caps) {

		String browser = WebConfiguration.getBrowserName();

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver((ChromeOptions) caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver((EdgeOptions) caps);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver((FirefoxOptions) caps);
			break;
		default:
			throw new IllegalArgumentException("Provided browser '" + browser + "' is not supported.");
		}

		return driver;
	}

	public static WebDriver buildRemote(MutableCapabilities caps) throws MalformedURLException {
		return new RemoteWebDriver(new URL(WebConfiguration.getRemoteURL()), caps);
	}

	public static void DestroyDriver() {
		driver.quit();
	}

}
