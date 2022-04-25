package com.cucumber.listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cucumber.managers.FileReaderManager;
import com.cucumber.managers.WebDriverManagers;
import com.cucumber.reporter.CaptureReports;
import com.cucumber.reporter.CaptureScenariosStatus;
import com.cucumber.reporter.CaptureStepStatus;
import com.cucumber.reports.HTMLReportGenerator;
import com.cucumber.reports.ReportInterface;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class ConcurrentListener implements ConcurrentEventListener,Serializable {
    public static String currentStepName;
    public static List<String> currentScenarioStepList = null;
        
    public EventHandler<TestStepStarted> testStepStartedHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            handleTestStepStarted(event);
        }

    };

    public EventHandler<TestCaseStarted> testCaseStartedHandler = new EventHandler<TestCaseStarted>() {
        @Override
        public void receive(TestCaseStarted event) {
            handleTestStepStarted1(event);
        }

    };
    
    public EventHandler<TestRunFinished> testRunFinishedHandler = new EventHandler<TestRunFinished>() {
        @Override
        public void receive(TestRunFinished event) {
            try {
				handleTestRunFinished(event);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
        }

    };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, testStepStartedHandler);
        publisher.registerHandlerFor(TestCaseStarted.class, testCaseStartedHandler);
        publisher.registerHandlerFor(TestRunFinished.class, testRunFinishedHandler);
    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep)event.getTestStep();
            currentStepName = testStep.getStep().getText();
            
        }


    }
    
    private void handleTestStepStarted1(TestCaseStarted event) {
        if (event.getTestCase()!= null) {
            
        	currentScenarioStepList = new ArrayList<String>();
            List<TestStep> testStepList = event.getTestCase().getTestSteps();
            PickleStepTestStep step = null;
            for(int i=0;i<testStepList.size();i++)
            {
            	if (testStepList.get(i) instanceof PickleStepTestStep)
            	{
            	step = (PickleStepTestStep) testStepList.get(i);
            	currentScenarioStepList.add(step.getStep().getText());
            	}
            }
            
        }
    }
    
    public void handleTestRunFinished(TestRunFinished event) throws IOException, ClassNotFoundException {
    
    	ReportInterface HTMLReporter = new HTMLReportGenerator();
    	HTMLReporter.generateReport();
    	HTMLReporter.publishReport();
    	closeDriverInstance();
    	
    
    }
    
    private void closeDriverInstance() throws IOException, ClassNotFoundException
    {
    	WebDriverManagers driverManager = null;
    	
    	FileInputStream file = new FileInputStream(FileReaderManager.getFileReaderManager().getConfigFileReader().getSerializableFilePath());
        ObjectInputStream in = new ObjectInputStream(file);
                 
        driverManager = (WebDriverManagers)in.readObject();
          
        in.close();
        file.close();
        driverManager.getDriver().quit();
    }
    
    
    
    
}