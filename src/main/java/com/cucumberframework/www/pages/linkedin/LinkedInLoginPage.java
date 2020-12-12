package com.cucumberframework.www.pages.linkedin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumberframework.www.pages.base.BasePage;

import cucumber.api.Scenario;

public class LinkedInLoginPage extends BasePage {

	public LinkedInLoginPage(WebDriver browser) {
		super(browser);
		PageFactory.initElements(browser, this);
	}
	
	@FindBy(css="input#username")
	WebElement txtEmail;
	
	@FindBy(css="input#password")
	WebElement txtPassword;
	
	@FindBy(css="button[aria-label='Sign in']")
	WebElement btnSignIn;
	
	public void attemptLinkedInLogin(String userName, String password, Scenario currentScenario) {
		
	}

}
