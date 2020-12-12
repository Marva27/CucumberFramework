package com.cucumberframework.www.managers;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.pages.linkedin.LinkedInLoginPage;

public class PageObjectManager {
	
	private WebDriver browser;
	
	public LinkedInLoginPage linkedinLoginPage;
	
	public PageObjectManager(WebDriver browser) {
		this.browser = browser;
	}
	
	public LinkedInLoginPage getLinkedInLoginPage() {
		return (linkedinLoginPage == null) ? linkedinLoginPage = new LinkedInLoginPage(browser) : linkedinLoginPage;
	}

}
