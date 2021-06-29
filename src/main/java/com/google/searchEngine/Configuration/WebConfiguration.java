package com.google.searchEngine.Configuration;

public class WebConfiguration {

	private WebConfiguration() {
	}

	private static String get(String key) {
		return System.getProperty(key);
	}

	public static String getRemoteURL() {
		return get("env.remote.url");
	}

	public static String getBrowserName() {
		return get("env.browser.name");
	}

	public static String getDriverEnvironment() {
		return get("app.env");
	}

	public static String getPlatform() {
		return get("app.platform");
	}

}
