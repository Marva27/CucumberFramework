package com.cucumberframework.www.steps.hooks;

import com.cucumberframework.www.context.TestContext;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	TestContext testContext;
	
	public Hooks(TestContext context) {
		this.testContext = context;
	}
	
	@Before
	public void testSetup(Scenario scenario) {
		testContext.scenarioContext.setContext("currentScenario", scenario);
	}
	
	@After
	public void testTearDown() {
		testContext.getWebDriverManager().closeBrowser();
	}
	
}
