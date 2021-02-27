package com.cucumberframework.www.pages.linkedin;


import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cucumberframework.www.pages.base.BasePage;

import cucumber.api.Scenario;

public class MercuryFlightLoginPage extends BasePage {

	public MercuryFlightLoginPage(WebDriver browser) {
		super(browser);
		PageFactory.initElements(browser, this);
	}
	
@FindBy(css="input[name=\"userName\"]")
WebElement userName;

@FindBy(css="input[name=\"password\"]")
WebElement passWord;
	
@FindBy(css="input[name=\"submit\"]")
WebElement submitLogin;


	public void enterMercuryLogin(String UserID,String Password,Scenario currentScenario) throws IOException {
				
				enterValue(userName, UserID, currentScenario);
				enterValue(passWord, Password, currentScenario);				
		}
		
		public void clickMercurySubmit(Scenario currentScenario) throws IOException {
			
			eleClick(submitLogin, currentScenario);
		}
		
		public void verifyMercuryLogin(Scenario currentScenario) throws IOException {
			
				Assert.assertFalse(isDisplayed(submitLogin, currentScenario), "Login Successful");			
			
		}		


	
}
