package com.cucumberframework.www.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumberframework.www.utility.ReportGenerator;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
		features = "src/test/resource/features",
		glue = {"com.cucumberframework.www.steps"},		
		tags = {"@AttemptMercuryLogin"},
		plugin = {
				"pretty",
				"rerun:target/cucumber-reports/rerun.txt",
				"json:target/jsonReports/cucumber.json"
				}
		)

public class Runner {
		
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		//FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\screenshots\\"));
		//FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\target\\advnced-cucumber-report\\cucumber-html-reports\\embeddings\\"));
	}
	
	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}
	
	@DataProvider (parallel = false)
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
		System.out.println("Generating report....");
		ReportGenerator report = new ReportGenerator();
		report.generateReport();
		System.out.println("Done!!!!");
	}
	
	

}
