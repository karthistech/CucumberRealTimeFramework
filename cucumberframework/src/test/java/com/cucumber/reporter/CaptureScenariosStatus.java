package com.cucumber.reporter;

import java.util.ArrayList;
import java.util.List;

public class CaptureScenariosStatus {
	
	private String scenarioName;
	private String scenarioDescription;
	private int totalSteps;
	private String status;
	private List<CaptureStepStatus> captureStepStatusList;
	
	public CaptureScenariosStatus()
	{
		captureStepStatusList = new ArrayList<CaptureStepStatus>();
	}
	
	public String getScenarioName() {
		return scenarioName;
	}
	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}
	public String getScenarioDescription() {
		return scenarioDescription;
	}
	public void setScenarioDescription(String scenarioDescription) {
		this.scenarioDescription = scenarioDescription;
	}
	public int getTotalSteps() {
		return totalSteps;
	}
	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<CaptureStepStatus> getCaptureStepStatus() {
		return captureStepStatusList;
	}
	public void setCaptureStepStatus(CaptureStepStatus captureStepStatus) {
		this.captureStepStatusList.add(captureStepStatus);
	}
	

}
