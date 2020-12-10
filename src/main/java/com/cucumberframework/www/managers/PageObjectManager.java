package com.cucumberframework.www.managers;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.pages.GooglePage;

public class PageObjectManager {
	
	private WebDriver browser;
	
	public GooglePage googlePage;
	
	public PageObjectManager(WebDriver browser) {
		this.browser = browser;
	}
	
	public GooglePage getGooglePage() {
		return (googlePage == null) ? googlePage = new GooglePage(browser) : googlePage;
	}

}
