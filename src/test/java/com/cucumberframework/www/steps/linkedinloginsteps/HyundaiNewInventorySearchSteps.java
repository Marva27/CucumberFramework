package com.cucumberframework.www.steps.linkedinloginsteps;

import java.nio.file.Files;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.cucumberframework.www.context.TestContext;
import com.cucumberframework.www.managers.FileReaderManager;
import com.cucumberframework.www.pages.linkedin.HyundaiHomePage;
import com.cucumberframework.www.pages.linkedin.HyundaiSearchResultPage;
import com.cucumberframework.www.utility.Utility;

import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HyundaiNewInventorySearchSteps {

	
		TestContext testContext;	
		HyundaiHomePage hyundaiHomePage;
		HyundaiSearchResultPage hyundaiSearchResultPage;
		
		WebDriver browser;
		Scenario currentScenario;
		
		public HyundaiNewInventorySearchSteps(TestContext context) {
			this.testContext = context;
			this.currentScenario = (Scenario) testContext.getScenarioContext().getContext("currentScenario");
			hyundaiHomePage = testContext.getPageObjectManager().getHyundaiHomePage();
			hyundaiSearchResultPage = testContext.getPageObjectManager().getHyundaiSearchResultPage();
		}

		
		
		@Given("^I navigate to hyundai home page$")
		public void i_navigate_to_hyundai_home_page() throws Throwable {
			browser = testContext.getWebDriverManager().getBrowser();
		    if(System.getProperty("environment").equalsIgnoreCase("test"))
		    	browser.get(FileReaderManager.getInstance().getConfigReader().getTestApplicationUrl());
		    if(System.getProperty("environment").equalsIgnoreCase("stage"))
		    	browser.get(FileReaderManager.getInstance().getConfigReader().getStageApplicationUrl());
		    if(System.getProperty("environment").equalsIgnoreCase("prod"))
		    	browser.get(FileReaderManager.getInstance().getConfigReader().getProdApplicationUrl());
		    currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
		}


		@When("^I click new inventory$")
		public void i_click_new_inventory() throws Throwable {
			hyundaiHomePage.selectNewInventory(currentScenario);
			hyundaiHomePage.verifyNewInventorySearchSuccess(currentScenario);
		}
		
		@When("^I select filter model as \"([^\"]*)\"$")
		public void i_select_filter_model_as(String model) throws Throwable {
			hyundaiSearchResultPage.applyFilter(model, currentScenario);
		}

		
		@When("^I select filter year as \"([^\"]*)\"$")
		public void i_select_filter_year_as(String year) throws Throwable {			
			hyundaiSearchResultPage.applyFilter(year, currentScenario);			
		}
		
		@Then("^I verify the total count of the search result match with listed items in the search result$")
		public void i_verify_the_total_count_of_the_search_result_match_with_listed_items_in_the_search_result() throws Throwable {			
			hyundaiSearchResultPage.verifyVehicleCount(currentScenario);
		}
		
}
