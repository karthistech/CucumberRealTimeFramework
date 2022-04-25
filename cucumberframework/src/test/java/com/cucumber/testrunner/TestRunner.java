package com.cucumber.testrunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;

/**
 * @author karthikeyan
 *
 */
@CucumberOptions(
        features = "Feature",
        glue = {"com.cucumber.stepdefinition"},
        //tags = {"~@Ignore"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
                "com.cucumber.listeners.ConcurrentListener"})
 
public class TestRunner extends CustomAbstractTestNGCucumberTests {
	
    private TestNGCucumberRunner testNGCucumberRunner;
  
        
    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
    	 testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    
   /* @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
     @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }*/
 
	/*
	 * @Test(groups = "cucumber", description = "Runs Cucumber Feature") public void
	 * feature() {
	 * 
	 * }
	 */
    
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
        
        
    }
}
    
