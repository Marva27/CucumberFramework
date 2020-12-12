package com.cucumberframework.www.steps.linkedinloginsteps;

import java.nio.file.Files;

import org.openqa.selenium.WebDriver;

import com.cucumberframework.www.context.TestContext;
import com.cucumberframework.www.managers.FileReaderManager;
import com.cucumberframework.www.pages.linkedin.LinkedInLoginPage;
import com.cucumberframework.www.utility.Utility;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LinkedInLoginSteps {
	
	TestContext testContext;
	
	LinkedInLoginPage linkedinLoginPage;
	
	WebDriver browser;
	
	Scenario currentScenario;
	
	public LinkedInLoginSteps(TestContext context) {
		this.testContext = context;
		this.currentScenario = (Scenario) testContext.getScenarioContext().getContext("currentScenario");
	}

	@Given("^I am on Linkedin Login page$")
	public void i_am_on_Linkedin_Login_page() throws Throwable {
	    browser = testContext.getWebDriverManager().getBrowser();
	    if(System.getProperty("environment").equalsIgnoreCase("test"))
	    	browser.get(FileReaderManager.getInstance().getConfigReader().getTestApplicationUrl());
	    if(System.getProperty("environment").equalsIgnoreCase("stage"))
	    	browser.get(FileReaderManager.getInstance().getConfigReader().getStageApplicationUrl());
	    if(System.getProperty("environment").equalsIgnoreCase("prod"))
	    	browser.get(FileReaderManager.getInstance().getConfigReader().getProdApplicationUrl());
	    currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
	}

	@When("^I enter my email address as \"([^\"]*)\", password as \"([^\"]*)\"$")
	public void i_enter_my_email_address_as_password_as(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click the Login button$")
	public void i_click_the_Login_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should see Linkedin Home page$")
	public void i_should_see_Linkedin_Home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
