package com.cucumber.stepdefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.reflect.FieldUtils;
import org.openqa.selenium.WebDriver;

import com.cucumber.context.TestContext;
import com.cucumber.listeners.ConcurrentListener;
import com.cucumber.managers.FileReaderManager;
import com.cucumber.reporter.CaptureReports;
import com.cucumber.reporter.CaptureScenariosStatus;
import com.cucumber.reporter.CaptureStepStatus;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;

public class Hooks {
	
	TestContext context;
	WebDriver driver;
	CaptureScenariosStatus captureScenarioStatus = new CaptureScenariosStatus();;
	CaptureReports reports;
	private int stepCount = 0;
	
	public Hooks(TestContext context) throws IOException
	{
		this.context = context;
		driver = context.getWebDriverManager().getDriver();
		driver.get(FileReaderManager.getFileReaderManager().getConfigFileReader().getApplicationUrl());
		//this.reports = this.context.getCaptureReports();
		serializeWebDriverManager(context);
		reports = CaptureReports.getCaptureReportsInstance();
	}
	
	public void serializeWebDriverManager(TestContext context) throws IOException
	{
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(new File(FileReaderManager.getFileReaderManager().getConfigFileReader().getSerializableFilePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
          
       try {
		out.writeObject(context.getWebDriverManager());
       } catch (IOException e) {
			e.printStackTrace();
	}finally
       {
		file.close();
		out.close();
       }
	}
	
	@Before
	public void beforeScenario(Scenario scenario)
	{
		stepCount = 0;
		captureScenarioStatus.setScenarioName(scenario.getName());
		addAllTestStepsToReporter(ConcurrentListener.currentScenarioStepList);
	}
	
	public void addAllTestStepsToReporter(List<String> testStepsList)
	{
		for (int i=0;i<testStepsList.size();i++) {
			CaptureStepStatus captureStepStatus = new CaptureStepStatus();
            captureStepStatus.setStepName(testStepsList.get(i).toString());
            captureScenarioStatus.setCaptureStepStatus(captureStepStatus);
        }
		
		
	}
	
	
	
	@AfterStep
	public void aftereachStep(io.cucumber.java.Scenario scenario)
	{
		String status = null;
			if (scenario.getStatus() == Status.PASSED)
			{
				status = Status.PASSED.toString();
				
			} else if(scenario.getStatus() == Status.FAILED)
			{
				status = Status.FAILED.toString();
			} else if(scenario.getStatus() == Status.SKIPPED)
			{
				status = Status.SKIPPED.toString();
				
			} else if(scenario.getStatus() == Status.PENDING)
			{
				status = Status.PENDING.toString();
				
			} else if(scenario.getStatus() == Status.AMBIGUOUS)
			{
				status = Status.AMBIGUOUS.toString();
				
			} else if(scenario.getStatus() == Status.UNUSED)
			{
				status = Status.UNUSED.toString();
				
			} else if(scenario.getStatus() == Status.UNDEFINED)
			{
				status = Status.UNDEFINED.toString();
				
			} 
			captureScenarioStatus.getCaptureStepStatus().get(stepCount).setStatus(status);
			stepCount = stepCount+1;
	}
	
	@After
	public void afterScenario(Scenario scenario)
	{
		String status = null;
		if (scenario.getStatus() == Status.PASSED)
		{
			status = Status.PASSED.toString();
			
		}else if(scenario.getStatus() == Status.FAILED)
		{
			status = Status.FAILED.toString();
		} else if(scenario.getStatus() == Status.SKIPPED)
		{
			status = Status.SKIPPED.toString();
			
		} else if(scenario.getStatus() == Status.PENDING)
		{
			status = Status.PENDING.toString();
			
		} else if(scenario.getStatus() == Status.AMBIGUOUS)
		{
			status = Status.AMBIGUOUS.toString();
			
		} else if(scenario.getStatus() == Status.UNUSED)
		{
			status = Status.UNUSED.toString();
			
		} else if(scenario.getStatus() == Status.UNDEFINED)
		{
			status = Status.UNDEFINED.toString();
			
		}
		captureScenarioStatus.setStatus(status);
		reports.addCaptureReportsToList(captureScenarioStatus);
		
	}
	
	

}
