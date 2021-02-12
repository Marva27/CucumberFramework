package com.cucumberframework.www.managers;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.pages.linkedin.ExpediaLoginPage;
import com.cucumberframework.www.pages.linkedin.ExpediaSearchPage;
import com.cucumberframework.www.pages.linkedin.MercuryFlightLoginPage;


public class PageObjectManager {
	
	private WebDriver browser;
	
	public ExpediaLoginPage expediaLoginPage;
	public ExpediaSearchPage expediaSearchPage;
	public MercuryFlightLoginPage mercuryFlightLoginPage;
	
	
	public PageObjectManager(WebDriver browser) {
		this.browser = browser;
	}
	
	public ExpediaLoginPage getExpediaLoginPage() {
		return (expediaLoginPage == null) ? expediaLoginPage = new ExpediaLoginPage(browser) : expediaLoginPage;
	}

	public ExpediaSearchPage getExpediaSearchPage() {
		return (expediaSearchPage == null) ? expediaSearchPage = new ExpediaSearchPage(browser) : expediaSearchPage;
	}

	public MercuryFlightLoginPage getMercuryFlightLoginPage() {
		return (mercuryFlightLoginPage == null) ? mercuryFlightLoginPage = new MercuryFlightLoginPage(browser) : mercuryFlightLoginPage;
	}
}
