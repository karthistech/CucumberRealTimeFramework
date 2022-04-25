package com.cucumber.reports;

import com.cucumber.reporter.CaptureReports;

public interface ReportInterface {
	
	void generateReport();
	void publishReport();
default CaptureReports getReportContent()
{
	CaptureReports reportContent =  CaptureReports.getCaptureReportsInstance();
	if(reportContent == null)
	{
		throw new RuntimeException("Report Content is not available to generate report");
	}
	
	return reportContent;
}
}
