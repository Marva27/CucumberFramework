package com.cucumberframework.www.managers;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.pages.linkedin.HyundaiHomePage;
import com.cucumberframework.www.pages.linkedin.HyundaiSearchResultPage;
import com.cucumberframework.www.pages.linkedin.MercuryFlightLoginPage;



public class PageObjectManager {
	
	private WebDriver browser;
	

	public MercuryFlightLoginPage mercuryFlightLoginPage;
	public HyundaiHomePage hyundaiHomePage;
	public HyundaiSearchResultPage hyundaiSearchResultPage;
	
	
	public PageObjectManager(WebDriver browser) {
		this.browser = browser;
	}
	

	public MercuryFlightLoginPage getMercuryFlightLoginPage() {
		return (mercuryFlightLoginPage == null) ? mercuryFlightLoginPage = new MercuryFlightLoginPage(browser) : mercuryFlightLoginPage;
	}
	
	public HyundaiHomePage getHyundaiHomePage() {
		return (hyundaiHomePage == null) ? hyundaiHomePage = new HyundaiHomePage(browser) : hyundaiHomePage;
	}
	
	public HyundaiSearchResultPage getHyundaiSearchResultPage() {
		return (hyundaiSearchResultPage == null) ? hyundaiSearchResultPage = new HyundaiSearchResultPage(browser) : hyundaiSearchResultPage;
	}
}
