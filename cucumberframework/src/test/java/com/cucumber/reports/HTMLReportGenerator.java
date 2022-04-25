package com.cucumber.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cucumber.managers.FileReaderManager;
import com.cucumber.reporter.CaptureReports;
import com.cucumber.reporter.CaptureScenariosStatus;
import com.cucumber.reporter.CaptureStepStatus;

public class HTMLReportGenerator implements ReportInterface{

	StringBuilder html = new StringBuilder();
	@Override
	public void generateReport() {
		CaptureReports reportContent = this.getReportContent();
		
		List<CaptureScenariosStatus> scenarioList = reportContent.getCaptureReportsList();
    	
    	 html.append( "<!doctype html>\n" );
         html.append( "<html lang='en'>\n" );

         html.append( "<head>\n" );
         html.append( "<meta charset='utf-8'>\n" );
         html.append( "<title>Test Execution Report</title>\n" );
        
         html.append( "<h1>List of Reports</h1>\n" );
         html.append("<style type=\"text/css\">\r\n"
         		+ "\r\n"
         		+ "        .test-result-table {\r\n"
         		+ "\r\n"
         		+ "            border: 1px solid black;\r\n"
         		+ "            width: 800px;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-table-header-cell {\r\n"
         		+ "\r\n"
         		+ "            border-bottom: 1px solid black;\r\n"
         		+ "            background-color: silver;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-step-command-cell {\r\n"
         		+ "\r\n"
         		+ "            border-bottom: 1px solid gray;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-step-description-cell {\r\n"
         		+ "\r\n"
         		+ "            border-bottom: 1px solid gray;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-step-result-cell-ok {\r\n"
         		+ "\r\n"
         		+ "            border-bottom: 1px solid gray;\r\n"
         		+ "            background-color: green;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-step-result-cell-failure {\r\n"
         		+ "\r\n"
         		+ "            border-bottom: 1px solid gray;\r\n"
         		+ "            background-color: red;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-step-result-cell-notperformed {\r\n"
         		+ "\r\n"
         		+ "            border-bottom: 1px solid gray;\r\n"
         		+ "            background-color: white;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-result-describe-cell {\r\n"
         		+ "            background-color: tan;\r\n"
         		+ "            font-style: italic;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        .test-cast-status-box-ok {\r\n"
         		+ "            border: 1px solid black;\r\n"
         		+ "            float: left;\r\n"
         		+ "            margin-right: 10px;\r\n"
         		+ "            width: 45px;\r\n"
         		+ "            height: 25px;\r\n"
         		+ "            background-color: green;\r\n"
         		+ "        }\r\n"
         		+ "\r\n"
         		+ "        </style>");
         html.append( "</head>\n\n" );

         html.append( "<body>\n" );
         html.append( "<h1 class=\"test-results-header\">\r\n"
         		+ "            Test Report\r\n"
         		+ "        </h1>" );
         html.append( "<table class=\"test-result-table\" cellspacing=\"0\">" );
         html.append("<thead>\r\n"
         		+ "       <tr>");
         html.append("<td class=\"test-result-table-header-cell\">\r\n"
         		+ "                        S.No r\n"
         		+ "                    </td>\r\n"
         		+ "                    <td class=\"test-result-table-header-cell\">\r\n"
         		+ "                        Test Case\r\n"
         		+ "                    </td>\r\n"
         		+ "                    <td class=\"test-result-table-header-cell\">\r\n"
         		+ "                        Description\r\n"
         		+ "                    </td>\r\n"
         		+ "                    <td class=\"test-result-table-header-cell\">\r\n"
         		+ "                        Steps\r\n"
         		+ "                    </td>\r\n"
         		+ "                    <td class=\"test-result-table-header-cell\">\r\n"
         		+ "                        Status\r\n"
         		+ "                    </td>\r\n"
         		+ "                </tr>\r\n"
         		+ "            </thead>");
         html.append("<tbody>\r\n");
         
         
         html.append( "</html>" );

         //return html.toString();
    	
    	for(CaptureScenariosStatus scenario : scenarioList)
    	{
    		html.append("<tr class=\"test-result-step-row test-result-step-row-altone\">\r\n"
             		+ "                    <td class=\"test-result-step-command-cell\">\r\n"
             		+ "                        "+ scenario.getScenarioName()+" /\r\n"
             		+ "                    </td>\r\n"
             		+ "                    <td class=\"test-result-step-description-cell\">\r\n"
             		+ "                        "+scenario.getScenarioDescription()+" \"/\"\r\n"
             		+ "                    </td>\r\n"
             		+ "                    <td class=\"test-result-step-result-cell-ok\">\r\n"
             		+ "                        "+scenario.getTotalSteps()+"\r\n"
             		+ "                    </td>\r\n"
             		+ "                    <td class=\"test-result-step-result-cell-ok\">\r\n"
             		+ "                        "+scenario.getStatus()+"\r\n"
             		+ "                    </td>\r\n"
             		+ "                </tr>");
    		System.out.println("Name : "+ scenario.getScenarioName());
    		System.out.println("Description : "+ scenario.getScenarioDescription());
    		System.out.println("Total Steps : "+ scenario.getTotalSteps());
    		System.out.println("Status : "+ scenario.getStatus());
    		
    		for(CaptureStepStatus step : scenario.getCaptureStepStatus())
    		{
    			System.out.println("StepName : " +step.getStepName()+ " Step Status : "+ step.getStatus() + "Error Description : " + step.getErrorDescription());
    		}
    		
    	}
    	
    }
		
	
	@Override
	public void publishReport() {
		FileWriter publishHTML = null;
		File HTMLReportFile;
		String HTMLReportPath = getFilePath().replace(":", "-");
		String directoryName = HTMLReportPath.replace(HTMLReportPath.substring(HTMLReportPath.lastIndexOf("/")), "");
		
		try {
			HTMLReportFile = new File(directoryName);
			if (!HTMLReportFile.exists())
			{
				if (!HTMLReportFile.mkdirs())
					throw new RuntimeException("Unable to Create a Directory for HTML reports");
				
			}
			HTMLReportFile = new File(HTMLReportPath);
			if (!HTMLReportFile.exists())
			{
				if(!HTMLReportFile.createNewFile())
					throw new RuntimeException("File Creation for HTML report is failed");
			}
			publishHTML = new FileWriter(HTMLReportFile);
			BufferedWriter bw = new BufferedWriter(publishHTML);
	        bw.write(html.toString());
	        bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException ("HTML Report File Creation has failed" );
		}finally
		{
			try {
			
				publishHTML.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private String getFilePath()
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-YYYY hh:mm");
		String HTMLReportPath = FileReaderManager.getFileReaderManager().getConfigFileReader().getHTMLReportFilePath();
		
		if (HTMLReportPath.substring(HTMLReportPath.lastIndexOf(".")).equalsIgnoreCase(".HTML"))
		{
			HTMLReportPath = HTMLReportPath.replace(HTMLReportPath.substring(HTMLReportPath.lastIndexOf(".")), "");
		}
		
		return HTMLReportPath.concat(formatter.format(date).concat(".html"));
		
	}

}
