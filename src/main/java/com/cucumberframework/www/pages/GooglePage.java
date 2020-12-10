package com.cucumberframework.www.pages;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.pages.base.BasePage;

public class GooglePage extends BasePage{

	public WebDriver browser;
	
	public GooglePage(WebDriver browser) {
		super(browser);
	}
	
}
