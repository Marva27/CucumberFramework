package com.cucumberframework.www.steps.linkedinloginsteps;

import java.nio.file.Files;
import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.context.TestContext;
import com.cucumberframework.www.managers.FileReaderManager;
import com.cucumberframework.www.pages.linkedin.MercuryFlightLoginPage;
import com.cucumberframework.www.utility.Utility;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.Scenario;

public class MercuryFlightBookingSteps {
	
	TestContext testContext;	
	MercuryFlightLoginPage mercuryFlightLoginPage;
	WebDriver browser;
	Scenario currentScenario;
	
	public MercuryFlightBookingSteps(TestContext context) {
		this.testContext = context;
		this.currentScenario = (Scenario) testContext.getScenarioContext().getContext("currentScenario");
		mercuryFlightLoginPage = testContext.getPageObjectManager().getMercuryFlightLoginPage();
	}
	
	
	@Given("^I am on Mercury Login page$")
	public void i_am_on_Mercury_Login_page() throws Throwable {
		 browser = testContext.getWebDriverManager().getBrowser();
	    if(System.getProperty("environment").equalsIgnoreCase("test"))
	    	browser.get(FileReaderManager.getInstance().getConfigReader().getTestApplicationUrl());
	    if(System.getProperty("environment").equalsIgnoreCase("stage"))
	    	browser.get(FileReaderManager.getInstance().getConfigReader().getStageApplicationUrl());
	    if(System.getProperty("environment").equalsIgnoreCase("prod"))
	    	browser.get(FileReaderManager.getInstance().getConfigReader().getProdApplicationUrl());
	    currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
	}

	
	@When("^I enter my username as \"([^\"]*)\", password as \"([^\"]*)\"$")
	public void i_enter_my_username_as_password_as(String UserID, String Password) throws Throwable {
	mercuryFlightLoginPage.enterMercuryLogin(UserID, Password, currentScenario);
	   
	}

	@And("^I click the Submit button$")
	public void i_click_the_Submit_button() throws Throwable {
		mercuryFlightLoginPage.clickMercurySubmit(currentScenario);
	   
	}

	@Then("^I should not see the submit$")
	public void i_should_not_see_the_submit() throws Throwable {
	
		mercuryFlightLoginPage.verifyMercuryLogin(currentScenario);

	}
	
	
}
