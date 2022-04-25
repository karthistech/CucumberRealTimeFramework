package com.cucumber.context;

import java.io.Serializable;

import com.cucumber.managers.PageObjectManagers;
import com.cucumber.managers.TestDataReaderManager;
import com.cucumber.managers.WebDriverManagers;
import com.cucumber.reporter.CaptureReports;
import com.cucumber.testdata.StepDefDataShare;


public class TestContext{
 /**
	 * 
	 */
	
private WebDriverManagers webDriverManager;
 private PageObjectManagers pageObjectManager;
 private TestDataReaderManager testDataReaderManager;
 private StepDefDataShare stepDefDataShare;
 private CaptureReports captureReports;
 
 public TestContext(){
 webDriverManager = new WebDriverManagers();
 pageObjectManager = new PageObjectManagers(webDriverManager.getDriver());
 testDataReaderManager = new TestDataReaderManager();
 stepDefDataShare = new StepDefDataShare();
 //captureReports = new CaptureReports();
 }
 
 public WebDriverManagers getWebDriverManager() {
 return webDriverManager;
 }
 
 public PageObjectManagers getPageObjectManager() {
 return pageObjectManager;
 }
 
 public TestDataReaderManager getTestDataReaderManager() {
	 return testDataReaderManager;
 } 
 
 public StepDefDataShare getStepDefDataShare() {
	 return stepDefDataShare;
 } 
 
	/*
	 * public CaptureReports getCaptureReports() { return captureReports; }
	 */
 
}