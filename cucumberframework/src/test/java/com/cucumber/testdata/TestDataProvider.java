package com.cucumber.testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cucumber.managers.FileReaderManager;
import com.cucumber.utilities.ExcelFileReader;

public class TestDataProvider {

	ExcelFileReader excel;
	
	public TestDataProvider()
	{
		excel = new ExcelFileReader();
	}
	
	public List<String> getFinalScenarioToTest()
	{
		Map<String,Map<String,List<String>>> excelData = new HashMap<String,Map<String,List<String>>>();
		Map<String,List<String>> sheetData = new HashMap<String,List<String>>();
		List<String> scenariosToTestFinal = new ArrayList<String>();
		String sheetName = FileReaderManager.getFileReaderManager().getConfigFileReader().getScenarioExcelFileSheetName();
		String scenarioSelectKeyword = FileReaderManager.getFileReaderManager().getConfigFileReader().getSelectScenarioKeyword();
		
		excelData = excel.getDataFromExcel(sheetName);
		sheetData = excelData.get(sheetName);
		
		for(Map.Entry<String, List<String>> sheetDataMapElement: sheetData.entrySet())
		{
			if(sheetDataMapElement.getValue().get(1).equalsIgnoreCase(scenarioSelectKeyword))
			{
				scenariosToTestFinal.add(sheetDataMapElement.getValue().get(0));
				
			}
		}
		if (scenariosToTestFinal.size() == 0)
		{
			throw new RuntimeException ("No Scenarios were selected to run");
		}
		return scenariosToTestFinal;
	}
	
	public List<String> getTestData(String testDataKeyWord)
	{
		Map<String,Map<String,List<String>>> excelData = new HashMap<String,Map<String,List<String>>>();
		Map<String,List<String>> sheetData = new HashMap<String,List<String>>();
		List<String> TestData = new ArrayList<String>();
		String sheetName = FileReaderManager.getFileReaderManager().getConfigFileReader().getTestDataSheetname();
		
		excelData = excel.getDataFromExcel(sheetName);
		sheetData = excelData.get(sheetName);
		
		TestData = sheetData.get(testDataKeyWord);
				
		if (TestData.size() == 0)
		{
			throw new RuntimeException ("TestDataKeyWord doesnt have data to return");
		}
		return TestData;
	}
}
