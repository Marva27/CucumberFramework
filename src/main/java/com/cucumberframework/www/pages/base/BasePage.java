package com.cucumberframework.www.pages.base;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cucumberframework.www.utility.Utility;

import cucumber.api.Scenario;
import cucumber.runtime.snippets.Concatenator;

public class BasePage {
	
	public WebDriver browser;
	
	public BasePage(WebDriver browser) {
		this.browser = browser;
	}
	
	/*
	 * Function Name: isAlertPresent
	 * Objective: To verify if an alert box is displayed
	 * Date Created: 01/28/2020 Date Modified: 01/28/2020
	 * Changes Made: Initial version
	 */
	public boolean isAlertPresent() {
		try {
			browser.switchTo().alert();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean verifyPartialLabelText(WebElement label, String expectedValue, Scenario currentScenario) throws IOException {
		try {
			if(label.getText().replaceAll("\\s", "").contains(expectedValue.replaceAll("\\s", ""))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element due to " + e.getMessage());
			return false;
		}
	}
	
	/*
	 * Function Name: selectDropDown
	 * Objective: To select a value from drop down
	 * Date Created: 03/14/2019
	 * Date Modified: 01/27/2020
	 * Changes Made: Stop execution if a value is NOT present in the dropdown
	 */
	public void selectDropDown(WebElement element, String optionValue, Scenario currentScenario) throws IOException {
		waitForAnElementToBeVisible(element, 5, currentScenario);
		Select select = new Select(element);
		try {
			select.selectByVisibleText(optionValue);
		}catch(Exception e) {
			scrollToElement(element, currentScenario);
			element.click();
			Assert.fail(optionValue + " was NOT found in dropdown");
		}
	}
	
	/*
	 * Function Name: getTextBoxValue
	 * Objective: To get the value from a text box
	 * Date Created: 04/12/2020 Date Modified: 04/12/2020
	 * Changes Made: Initial version
	 */
	
	public String getTextBoxValue(WebElement element, Scenario currentScenario) throws IOException {
		try {
			return element.getAttribute("value");
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element due to " + e.getMessage());
		}
		return null;
	}
	
	/*
	 * Function Name: verifyTextBoxContents
	 * Objective: To verify the contents of a text field
	 * Date Created: 01/22/2020 Date Modified: 01/22/2020
	 * Changes Made: Initial version
	 */
	
	public void verifyTextBoxContents(WebElement element, String expectedValue, Scenario currentScenario) throws IOException {
		try {
			if(expectedValue.trim().equals("")) {
				Assert.assertEquals(element.getAttribute("value"), "", "Is textfield value set to " + expectedValue + "?");
			} else {
				Assert.assertEquals(element.getAttribute("value"), expectedValue, "Is textfield value set to " + expectedValue + "?");
			}
		} catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation engineer.");
		}
	}
	
	/*
	 * Function Name: verifyDropDownValue
	 * Objective: To verify the active selected value in dropdown
	 * Date Created: 01/22/2020 Date Modified: 01/22/2020
	 * Changes Made: Initial version
	 */
	
	public void verifyDropDownValue(WebElement element, String expectedValue, Scenario currentScenario) throws IOException {
		try {
			Select select = new Select(element);
			Assert.assertEquals(select.getFirstSelectedOption().getText(), expectedValue, "Is dropdown set to " + expectedValue + "?");	
		} catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation engineer. Reason is: " + e.getMessage());
		}
	}
	
	/*
	 * Function Name: verifyPartialDropDownValue
	 * Objective: To verify if the active selected value partially match with the expected value
	 * Date Created: 04/09/2020 Date Modified: 04/09/2020
	 * Changes Made: Initial version
	 */
	
	public void verifyPartialDropDownValue(WebElement element, String expectedValue, Scenario currentScenario) throws IOException {
		try {
			Select select = new Select(element);
			Assert.assertTrue(select.getFirstSelectedOption().getText().contains(expectedValue), "Is dropdown contains " + expectedValue + "?");	
		} catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation engineer. Reason is: " + e.getMessage());
		}
		
	}

	
	/*
	 * Function Name: clickUsingActionsClass
	 * Objective: To click a web element using Actions class
	 * Date Created: 04/17/2020 Date Modified: 04/17/2020
	 * Changes Made: Initial version
	 */
	public void clickElementUsingActionsClass(WebElement element, Scenario currentScenario) throws IOException {
		try	{
			Actions actions = new Actions(browser);
			actions.moveToElement(element).click().build().perform();
		}catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer. Reason is: " + e.getMessage());
		}
	}
	
	/*
	 * Function Name: getFormattedValue
	 * Objective: To remove $ and , symbols from a value
	 * Date Created: 04/10/2020 Date Modified: 04/10/2020
	 * Changes Made: Initial version
	 */
	public String getFormattedValue(String inputText) {
		inputText = inputText.replaceAll("\\$", "").replaceAll("\\,", "");
		double value = Double.parseDouble(inputText);
		value = Math.round(value);
		inputText = String.valueOf(value);
		if(inputText.contains("."))
			return inputText.substring(0, inputText.indexOf("."));
		else
			return inputText;
	}
	
	public boolean waitForAnElementToBeVisibleIgnoringErrors(WebElement element, int seconds, Scenario currentScenario) {
		int i = 0;
		boolean staleElement = true;
		while (staleElement && i < 2) {
			try {
				browser.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(browser, seconds);
				wait.until(ExpectedConditions.visibilityOf(element));
				return true;
			}catch(Exception e) {
				i++;
				staleElement = true;
				if(i >= 2) {
					return false;
				}
			}
		}
		return false;
	}
	
	/*
	 * Function Name: enterValue
	 * Objective: To enter value in a text field
	 * Date Created: 03/07/2019
	 * Date Modified: 03/07/2019
	 * Changes Made: Initial version
	 */
	public void enterValue(WebElement element, String value, Scenario currentScenario) throws IOException {
		try {
			if(!element.getAttribute("value").equals(""))
				element.clear();
			element.sendKeys(value);
		}catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer.");
		}
	}
	
	/*
	 * Function Name: verifyLabelText
	 * Objective: To match the label text
	 * Date Created: 01/15/2020
	 * Date Modified: 01/15/2020
	 * Changes Made: Initial version
	 */
	public boolean verifyLabelText(WebElement element, String labelText, Scenario currentScenario) throws IOException {
		try {
			if(element.getText().replaceAll("\\s", "").equals(labelText.replaceAll("\\s", ""))) return true;
			else return false;
		}catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer. Reason is : " + e.getMessage());
			return false;
		}
	}
	
	/*
	 * Function Name: isElementDisappeared
	 * Objective: To wait for an element to disappear after specified seconds
	 * Date Created: 01/16/2020 Date Modified: 01/16/2020
	 * Changes Made: Initial version
	 */
	
	public boolean isElementDisappeared(WebElement element, int seconds) {
		try {
			browser.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(browser, seconds);
			wait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * Function Name: selectDropDownUsingJS
	 * Objective: To select a value from drop down using Javascript
	 * Date Created: 01/28/2020
	 * Date Modified: 01/28/2020
	 * Changes Made: Initial version
	 */
	public void selectDropDownUsingJS(WebElement element, String optionValue, Map<String, String> map, Scenario currentScenario) throws IOException {
		Select select = new Select(element);
		try {
			((JavascriptExecutor) browser).executeScript("arguments[0].value='" + map.get(optionValue) + "';",select);
		}catch(Exception e) {
			scrollToElement(element, currentScenario);
			element.click();
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail(optionValue + " was NOT found in dropdown");
		}
	}
	
	/*
	 * Function Name: isElementDisplayed
	 * Objective: To check if a WebElement is visible
	 * Date Created: 03/08/2019
	 * Date Modified: 03/08/2019
	 * Changes Made: Initial version
	 */
	public boolean isElementDisplayed(WebElement element) {
		browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {
			if(element.isDisplayed()){
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/*
	 * Function Name: enterValueUsingJS
	 * Objective: To enter value in a text field using Java script
	 * Date Created: 01/15/2020
	 * Date Modified: 01/15/2020
	 * Changes Made: Initial version
	 */
	public void enterValueUsingJS(WebElement element, String value) throws IOException {
		try {
			JavascriptExecutor runJS= ((JavascriptExecutor) browser);
			runJS.executeScript("arguments[0].value = '" + value + "'", element);
		}catch(Exception e) {
			Assert.fail("Error in locating element. Please contact automation developer. Reason is: " + e.getMessage());
		}
	}
	
	/*
	 * Function Name: getActiveSelected
	 * Objective: To get a currently selected value from a drop down
	 * Date Created: 03/19/2019
	 * Date Modified: 03/19/2019
	 * Changes Made: Initial version
	 */
	public String getActiveSelected(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();	
	}
	
	/*
	 * Function Name: clickElementUsingJS
	 * Objective: To click a web element using Java Script Executor
	 * Date Created: 03/07/2019
	 * Date Modified: 03/07/2019
	 * Changes Made: Initial version
	 */
	public void clickElementUsingJS(WebElement element, Scenario currentScenario) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) browser;
			js.executeScript("arguments[0].click()", element);
		}catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer due to reason " + e.getMessage());
		}
	}
	
	/*
	 * Function Name: selectDropDownByValue
	 * Objective: To select an option from drop down by its value
	 * Date Created: 02/03/2020 Date Modified: 02/03/2020
	 * Changes Made: Initial version
	 */
	
	public void selectDropDownByValue(WebElement element, String optionValue, Scenario currentScenario) throws IOException {
		Select select = new Select(element);
		try {
			select.selectByValue(optionValue);
		}catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail(optionValue + " was NOT found in dropdown");
		}	
	}
	
	/*
	 * Function Name: clickElement
	 * Objective: To click a web element
	 * Date Created: 03/07/2019
	 * Date Modified: 03/07/2019
	 * Changes Made: Initial version
	 */
	public void clickElement(WebElement element, Scenario currentScenario) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(browser, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			//scrollToElement(element, currentScenario);
			element.click();
		}catch(Exception e) {
			if(e instanceof StaleElementReferenceException) {
				WebDriverWait wait = new WebDriverWait(browser, 2);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
			} else {
				currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
				Assert.fail("Error in locating element. Please contact automation developer. Reason is: " + e.getMessage());
			}
		}
	}
	
	/*
	 * Function Name: waitForAnElementToBeVisible
	 * Objective: To wait for an element to be visible
	 * Date Created: 03/19/2019
	 * Date Modified: 03/19/2019
	 * Changes Made: Initial version
	 */
	public boolean waitForAnElementToBeVisible(WebElement element, int seconds, Scenario currentScenario) throws IOException {
		int i = 0;
		boolean staleElement = true;
		while (staleElement && i < 2) {
			try {
				browser.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(browser, seconds);
				wait.until(ExpectedConditions.visibilityOf(element));
				return true;
			}catch(Exception e) {
				i++;
				staleElement = true;
				if(i >= 2) {
					currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
					return false;
				}
			}
		}
		return false;
	}
	
	/*
	 * Function Name: scrollToElement
	 * Objective: To scroll to a web element
	 * Date Created: 01/24/2020 Date Modified: 01/24/2020
	 * Changes Made: Initial version
	 */
	public void scrollToElement(WebElement element, Scenario currentScenario) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) browser;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch(Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in location element. Please contact automation developer. Reason is: " + e.getMessage());
		}
	
	}
	
	
	
	
	
	/*
	 * VJ's reusable action methods
	 */

	public boolean  isCheckBoxSelected (WebElement element) {		
		try {
			if (element.isSelected()) {
				return true;	
			}else {
				return false;
			}
		} catch (Exception e) {
	      System.out.println(e.getMessage());
	      return false;
		}		
		
	}	
	
	public void selectCheckBox (WebElement element,Scenario currentScenario) throws IOException {		
		try {
			if (!isCheckBoxSelected(element))
				element.click();
			
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer.");
		}
		
					
	}
	
	//verify an element is displayed on the page
	
	public boolean  isDisplayed (WebElement element,Scenario currentScenario) throws IOException {		
		try {
			if (element.isDisplayed()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
			//currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			//Assert.fail("Error in locating element. Please contact automation developer.");
		}
		//return false;	
		
	}	
	
	// MoreTravel menu selection
	
	public void eleClick(WebElement element, Scenario currentScenario) {
		element.click();		
	}
	
	// MoreTravel menu option selection
	public void menuOptionSelect(WebElement element, Scenario currentScenario) {
		element.click();		
	}
	
	
	//switch to pop up
	
	public void clickElementAndswitchToPopUp(WebElement element) {
		
		String mainWindowHandle=browser.getWindowHandle();
		element.click();
		Set<String> s = browser.getWindowHandles();
		 Iterator<String> ite = s.iterator();
         while(ite.hasNext())
         {
              String popupHandle=ite.next().toString();
              if(!popupHandle.contains(mainWindowHandle))
              {
                    browser.switchTo().window(popupHandle);
              }
         }
		
	}
	
	//month picker picks up the month and sets run time xpath
	
	public void datePicker(String TravelDate, Scenario currentScenario) throws IOException {			
		
		String date[] = TravelDate.split("-");
		//System.out.println(date[1]);
		String mmAndyyyy = date[1]+" "+date[2];
		System.out.println(mmAndyyyy);
		
		String month = date[1].substring(0,3)+" "+"1"+","+" "+date[2];
		System.out.println(month);
		
		String xpath ="//button[contains(@aria-label,\"trimMonth\")]";
		String xpath1 = xpath.replaceAll("trimMonth", month);
		System.out.println(xpath1);
		WebElement DateXpath = browser.findElement(By.xpath(xpath1));
		
		try {							
			DateXpath.click();			
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer.");
		}
				
		
	}
	
	
	//Adding passenger count
	
	public void addPassenger(WebElement element, int count, Scenario currentScenario) throws IOException {			
		
			
		try {							
			for (int i = 0;i<count;i++) {
				element.click();
			}
				
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer.");
		}
				
		
	}
	
	//Flight class Selection
	
	
	
	public void classSelection(String Flightclass, Scenario currentScenario) throws IOException {			
		
		
		try {							
			
			if(Flightclass.equals("Economy")) {
				browser.findElement(By.xpath("//*[@id=\"preferred-class-input\"]/div/div/a[1]")).click();					
			}else if(Flightclass.equals("PremiumEconomy")) {
				browser.findElement(By.xpath("//*[@id=\"preferred-class-input\"]/div/div/a[2]")).click();					
			}else if(Flightclass.equals("BusinessClass")) {
				browser.findElement(By.xpath("//*[@id=\"preferred-class-input\"]/div/div/a[3]")).click();					
			}else if(Flightclass.equals("FirstClass")) {
				browser.findElement(By.xpath("//*[@id=\"preferred-class-input\"]/div/div/a[4]")).click();			
			}				
				
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer.");
		}
				
		
	}
	
	//get text of an element and compare
	
	public  void tripTypeVerifyAndSelection(WebElement element,Scenario currentScenario) throws IOException {		
		try {
			String selectedTrip = element.getAttribute("aria-selected");
			if (selectedTrip.equals("true")) {				
			}else {
			   eleClick(element, currentScenario);
			}
		} catch (Exception e) {
			currentScenario.embed(Files.readAllBytes(Utility.captureScreenshot(browser, false).toPath()), "image/png");
			Assert.fail("Error in locating element. Please contact automation developer.");
		}		
	}
	
	
	
	
}

	



