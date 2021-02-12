package com.cucumberframework.www.pages.linkedin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cucumberframework.www.pages.base.BasePage;

import cucumber.api.Scenario;

public class LinkedInLoginPage extends BasePage {

	public LinkedInLoginPage(WebDriver browser) {
		super(browser);
		PageFactory.initElements(browser, this);
	}
	
	@FindBy(css="input#session_key")
	WebElement txtEmail;
	
	@FindBy(css="input#session_password")
	WebElement txtPassword;
	
	@FindBy(css="button.sign-in-form__submit-button")
	WebElement btnSignIn;
	
	@FindBy(css="div.profile-rail-card__actor-link.t-16.t-black.t-bold")
	WebElement profileName;
	
	
	public void attemptLinkedInLogin(String userName, String password, Scenario currentScenario) throws IOException, InterruptedException {
		enterValue(txtEmail, userName, currentScenario);
		enterValue(txtPassword, password, currentScenario);
		clickElement(btnSignIn, currentScenario);
		
	}

	public void enterLoginCredentials(String userName, String password, Scenario currentScenario) throws IOException, InterruptedException {
		Thread.sleep(500);
		enterValue(txtEmail, userName, currentScenario);
		enterValue(txtPassword, password, currentScenario);		
	}
	
	public void clickSignIn(Scenario currentScenario) throws IOException {
		clickElement(btnSignIn, currentScenario);
	}
	
	public void verifySignIn(String expProfileName, Scenario currentScenario) throws IOException {
		Assert.assertTrue(verifyLabelText(profileName, expProfileName, currentScenario), "does "+ expProfileName + " match?");
		
	}
}
