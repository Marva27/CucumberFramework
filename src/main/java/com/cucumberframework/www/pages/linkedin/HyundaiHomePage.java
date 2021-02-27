package com.cucumberframework.www.pages.linkedin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cucumberframework.www.pages.base.BasePage;

import cucumber.api.Scenario;

public class HyundaiHomePage extends BasePage {

	public HyundaiHomePage(WebDriver browser) {
		super(browser);
		PageFactory.initElements(browser, this);
		// TODO Auto-generated constructor stub
	}
	

@FindBy(css="img[alt=\"Huffines Hyundai Plano New Inventory\"]")
WebElement NewInventorylink;

@FindBy(css="h3#search-narrow_search")	
WebElement NarrowSearch;


	public	void selectNewInventory (Scenario	currentScenario) {		
		eleClick(NewInventorylink, currentScenario);		
	}
	
	public	void verifyNewInventorySearchSuccess(Scenario	currentScenario) throws IOException {		
		Assert.assertTrue(isDisplayed(NarrowSearch, currentScenario),"User is on the search result");		
	}
	
	

}
