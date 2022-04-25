package com.cucumber.reporter;

import java.util.ArrayList;
import java.util.List;

public class CaptureReports {
	
	private List<CaptureScenariosStatus> captureReports;
	private static CaptureReports reports;
	
	private CaptureReports()
	{
		captureReports = new ArrayList<CaptureScenariosStatus>();
	}
	
	public static CaptureReports getCaptureReportsInstance()
	{
		if (reports == null)
			reports = new CaptureReports();
		return reports;
	}
	public List<CaptureScenariosStatus> getCaptureReportsList()
	{
		return captureReports;
	}
	
	public void addCaptureReportsToList(CaptureScenariosStatus captureScenariosStatus)
	{
		captureReports.add(captureScenariosStatus);
	}

}
