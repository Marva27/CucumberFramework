package com.cucumberframework.www.managers;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.pages.linkedin.MercuryFlightLoginPage;


public class PageObjectManager {
	
	private WebDriver browser;
	

	public MercuryFlightLoginPage mercuryFlightLoginPage;
	
	
	public PageObjectManager(WebDriver browser) {
		this.browser = browser;
	}
	

	public MercuryFlightLoginPage getMercuryFlightLoginPage() {
		return (mercuryFlightLoginPage == null) ? mercuryFlightLoginPage = new MercuryFlightLoginPage(browser) : mercuryFlightLoginPage;
	}
}
