package com.cucumberframework.www.context;

import com.cucumberframework.www.managers.PageObjectManager;
import com.cucumberframework.www.managers.WebDriverManager;

public class TestContext {
	
	private WebDriverManager webDriverManager;
	private PageObjectManager pageObjectManager;
	public ScenarioContext scenarioContext;
	
	public TestContext() {
		webDriverManager = new WebDriverManager();
		pageObjectManager = new PageObjectManager(webDriverManager.getBrowser());
		scenarioContext = new ScenarioContext();
	}
	
	public WebDriverManager getWebDriverManager() {
		return webDriverManager;
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}

}
