package com.cucumber.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.cucumber.enume.DriverType;
import com.cucumber.enume.EnvironmentType;

public class ConfigFileReader {
	
	private static ConfigFileReader configFileReader;
	public Properties properties;
	public String propertyFilePath = "./config/config.properties";
	
	private ConfigFileReader()
	{
		 BufferedReader reader;
		 try {
			 reader = new BufferedReader(new FileReader(propertyFilePath));
			 properties = new Properties();
		 try {
			 properties.load(reader);
			 reader.close();
			 } catch (IOException e) {
			 e.printStackTrace();
			 }
			 } catch (FileNotFoundException e) {
			 e.printStackTrace();
			 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
			 } 
	}
	
	public static ConfigFileReader getConfigFileReaderInstance()
	{
		if (configFileReader == null)
		{
			configFileReader = new ConfigFileReader();
		}
		return configFileReader;
	}

public String getDriverPath(){
	 String driverPath = null;
	 switch (getBrowser())
 	 {
 	 case CHROME: 
 		driverPath = properties.getProperty(DriverType.CHROME.toString().toLowerCase()+"Path");
 		 break;
 	 case FIREFOX: 
 		driverPath = properties.getProperty(DriverType.FIREFOX.toString().toLowerCase()+"Path");
 		 break;
 	 case INTERNETEXPLORER: 
 		driverPath = properties.getProperty(DriverType.INTERNETEXPLORER.toString().toLowerCase()+"Path");
 		 break;
 	 }
	 if(driverPath!= null) return driverPath;
	 else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath"); 
	 }
	 
	 public long getImplicitlyWait() { 
	 String implicitlyWait = properties.getProperty("implicitlyWait");
	 if(implicitlyWait != null) {
	 try{
	 return Long.parseLong(implicitlyWait);
	 }catch(NumberFormatException e) {
	 throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
	 }
	 }
	 return 30; 
	 }
	 
	 public String getApplicationUrl() {
		 String url = null;
	 	 switch (getEnvironment())
	 	 {
	 	 case DEVELOPMENT: 
	 		 url = properties.getProperty(EnvironmentType.DEVELOPMENT.toString().toLowerCase()+"Url");
	 		 break;
	 	 case PROD: 
	 		 url = properties.getProperty(EnvironmentType.PROD.toString().toLowerCase()+"Url");
	 		 break;
	 	 case TESTING: 
	 		 url = properties.getProperty(EnvironmentType.TESTING.toString().toLowerCase()+"Url");
	 		 break;
	 	 case UAT: 
	 		 url = properties.getProperty(EnvironmentType.UAT.toString().toLowerCase()+"Url");
	 		 break;
	 } 
	 if(url != null) return url;
	 else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:Url");
	 }
	 
	 public DriverType getBrowser() {
	 String browserName = properties.getProperty("browser");
	 if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
	 else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
	 else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
	 else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	 }
	 
	 public EnvironmentType getEnvironment() {
	 String environmentName = properties.getProperty("environment");
	 if(environmentName == null || environmentName.equalsIgnoreCase("testing")) return EnvironmentType.TESTING;
	 else if(environmentName.equals("development")) return EnvironmentType.DEVELOPMENT;
	 else if(environmentName.equals("prod")) return EnvironmentType.PROD;
	 else if(environmentName.equals("uat")) return EnvironmentType.UAT;
	 else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	 }
	 
	 public Boolean getBrowserWindowSize() {
	 String windowSize = properties.getProperty("windowMaximize");
	 if(windowSize != null) return Boolean.valueOf(windowSize);
	 return true;
	 }
	 
	 public String getScenarioExcelPath()
	 {
		 String excelPath = properties.getProperty("scenarioExcelFilePath");
		 if (!excelPath.isEmpty()) {
			 return excelPath;
		 }else throw new RuntimeException("scenarioExcelFilePath key value in configuration.properties is not matched ");
			 
	 }
	 
	 public String getScenarioExcelFileSheetName()
	 {
		 String excelSheetName = properties.getProperty("scenarioExcelFileSheetName");
		 if (!excelSheetName.isEmpty()) {
			 return excelSheetName;
		 }else throw new RuntimeException("scenarioExcelFileSheetName key value in configuration.properties is not matched ");
			 
	 }
	
	 public String getSelectScenarioKeyword()
	 {
		 String excelSheetName = properties.getProperty("scenarioSelectKeyword");
		 if (!excelSheetName.isEmpty()) {
			 return excelSheetName;
		 }else throw new RuntimeException("scenarioSelectKeyword key value in configuration.properties is not matched ");
			 
	 }

	 public String getTestDataSheetname()
	 {
		 String excelSheetName = properties.getProperty("testDataSheetname");
		 if (!excelSheetName.isEmpty()) {
			 return excelSheetName;
		 }else throw new RuntimeException("testDataSheetName key value in configuration.properties is not matched ");
			 
	 }
	 
	 public String getSerializableFilePath()
	 {
		 String serializableFilePath = properties.getProperty("serializableFilePath");
		 if (!serializableFilePath.isEmpty()) {
			 return serializableFilePath;
		 }else throw new RuntimeException("serializableFilePath key value in configuration.properties is not matched ");
			 
	 }
	 
	 public String getHTMLReportFilePath()
	 {
		 String serializableFilePath = properties.getProperty("HTMLReportPath");
		 if (!serializableFilePath.isEmpty()) {
			 return serializableFilePath;
		 }else throw new RuntimeException("HTMLReportpath key value in configuration.properties is not matched ");
			 
	 }

}
