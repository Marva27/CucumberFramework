package com.cucumberframework.www.pages.linkedin;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumberframework.www.pages.base.BasePage;

import cucumber.api.Scenario;

public class HyundaiSearchResultPage extends BasePage {
	
	
		public HyundaiSearchResultPage(WebDriver browser) {
		super(browser);
		PageFactory.initElements(browser, this);
		// TODO Auto-generated constructor stub
	}


	
@FindAll(@FindBy(css="h4.toggle_filter_link.clearfix"))
List<WebElement> filterType;

		
     public void selectFilterType(String Model, Scenario currScenario) {
	 System.out.println(filterType.size());			
			
	 }

}
