package com.cucumberframework.www.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cucumberframework.www.enums.BrowserType;

public class WebDriverManager {
	
	
	private WebDriver browser;
	private static BrowserType browserType;
	
	public WebDriverManager() {
		browserType = FileReaderManager.getInstance().getConfigReader().getBrowser();
	}
	
	public WebDriver getBrowser() {
		if(browser == null) browser = createBrowser();
		return browser;
	}

	private WebDriver createBrowser() {
		switch(System.getProperty("execution")) {
		case "local": 
			browser = createLocalBrowser();
			break;
		case "remote": 
			browser = createRemoteDriver();
			break;
		}
		return browser;
	}

	private WebDriver createRemoteDriver() {
		String ipAddress = "10.130.50.35";
		RemoteWebDriver browser = null;
		DesiredCapabilities caps;
		String urlFormat = "http://" + ipAddress + ":" + 4444 + "/wd/hub";
		switch(browserType) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtenstion", false);
			caps = DesiredCapabilities.chrome();
			caps.setCapability(ChromeOptions.CAPABILITY, options);
			browser = new RemoteWebDriver(convertToURLFormat(urlFormat), caps);
		break;
		default:
			break;
		}
		return browser;
	}

	private CommandExecutor convertToURLFormat(String urlFormat) {
		// TODO Auto-generated method stub
		return null;
	}

	private WebDriver createLocalBrowser() {
		switch(browserType) {
		case FIREFOX:
			browser = new FirefoxDriver();
		break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			browser = new ChromeDriver();
		break;
		case INTERNETEXPLORER:
			browser = new InternetExplorerDriver();
		break;
		}
		browser.manage().window().maximize();
		return browser;
	}
	
	public void closeBrowser() {
		browser.close();
		browser.quit();
	}

}
