package com.cucumberframework.www.pages.linkedin;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cucumberframework.www.pages.base.BasePage;

import cucumber.api.Scenario;

public class HyundaiSearchResultPage extends BasePage {
	
	    BasePage basePage;
	    
		public HyundaiSearchResultPage(WebDriver browser) {
			super(browser);
			PageFactory.initElements(browser, this);
			// TODO Auto-generated constructor stub
		}
		
//@FindAll(@FindBy(css="h4.toggle_filter_link.clearfix"))
//List<WebElement> expandFilterType;

@FindAll(@FindBy(css="span.toggle_filter_icon.close"))
List<WebElement> expandFilterType;
		
@FindAll(@FindBy(css="a.thm-dark_text_color"))
List<WebElement> filterList;

@FindBy(css="div.srp_results_top_bx_first.fl_l")
WebElement totalVehicleCount;

@FindAll(@FindBy(css="div.srp_vehicle_wrapper.srp_vehicle_item_container.srp_results_design_v3"))
List<WebElement> vehicleList;

@FindBy(css="li.next")
WebElement nextlink;


	public void expandFilterType(Scenario currentScenario) throws IOException {
	 //System.out.println(expandFilterType.size());
	     int i=0;
		 while (i<=expandFilterType.size()-1) {
			 if (expandFilterType.get(i).getAttribute("style").contains("none")) {
				 clickElementUsingJS(expandFilterType.get(i),currentScenario);		
			 }
		 i++; 
	
		 }
		
	 }
	
	
	
     public void applyFilter(String filterSelect, Scenario currentScenario) throws IOException {
	 //System.out.println(filterList.size());	
	 expandFilterType(currentScenario);
	 		 for(WebElement e: filterList) {
			 //System.out.println(e.getText());
				  if(e.getText().contains(filterSelect)) {   //e.getAttribute("innerText");
					clickElementUsingJS(e, currentScenario);		
					break;
				  }		  
	 		 }

     }
     
     
     public void verifyVehicleCount(Scenario currentScenario) throws IOException {
    	 //System.out.println(totalVehicleCount.getText());
    	 
    	 String[] count = totalVehicleCount.getText().split("\\s+");  // Regular expression "\\s+"
    	 int totalCountInt=Integer.parseInt(count[0]);
    	 
    	 int sumOfvehicleCount=vehicleList.size();    	 
    	 while(isDisplayed(nextlink, currentScenario)) {    		 
    		 nextlink.click();
    		 waitForElementOrPage(totalVehicleCount, 5);
    		 sumOfvehicleCount=sumOfvehicleCount+vehicleList.size();    		 
    	 }
    	 
    	 
    	 if(totalCountInt==sumOfvehicleCount) {
			 System.out.println("Count of the total vehicle found"+"("+totalCountInt+")"+" is matched with count of listed vehicles"+"("+ sumOfvehicleCount +")"+" in the search result");
		 }else {
			 System.out.println("Count of the total vehicle found doesn't match with count listed vehicles in the search result");	    		
	     }    	 
    	 
    	 
    	 
     }
     
     
}