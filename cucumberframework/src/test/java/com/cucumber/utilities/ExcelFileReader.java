package com.cucumber.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cucumber.managers.FileReaderManager;


public class ExcelFileReader {

	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Map<String, Map<String, List<String>>> cacheExcelData;

	public ExcelFileReader() {
		cacheExcelData = new HashMap<String, Map<String, List<String>>>();
	}

	@SuppressWarnings("resource")
	private Workbook getWorkbook(String filePath) {
		String excelPath = filePath;
		FileInputStream fis;
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException("Scenario Excel file not found on the speified path");
		}
		if (excelPath.substring(excelPath.lastIndexOf(".")).equalsIgnoreCase(".xls")) {
			try {
				workbook = new HSSFWorkbook(fis);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (excelPath.substring(excelPath.lastIndexOf(".")).equalsIgnoreCase(".xlsx"))  {
			throw new RuntimeException("XLSX extension for Excel File has not been implemented");
		} else
			throw new RuntimeException(
					"Invalied file Specified in the configuration.properties for scenarioExcelFilePath key value");
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}

	private Sheet getSheet(String filePath, String sheetName) {
		workbook = getWorkbook(filePath);
		sheet = workbook.getSheet(sheetName);
		
		if ((sheet == null))
		{
			throw new RuntimeException ("the Sheet" + sheetName +"does not exist in the test Data Excel");
		}
		return sheet;
	}

	private Map<String,Map<String,List<String>>> cacheDataFromExcel(String filePath, String sheetName) {
		Map<String,List<String>> scenarioList = new HashMap<String,List<String>>();
		Map<String, Map<String, List<String>>> scenarioListMap = new HashMap<String, Map<String, List<String>>>();
		Cell cell;
		sheet = getSheet(filePath, sheetName);

		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			List<String> list = new ArrayList<String>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);

				if (cellContainsValue(cell))
				{
				switch (cell.getCellType()) {
				case NUMERIC:
					list.add("" + cell.getNumericCellValue());
					break;
				case STRING:
					list.add("" + cell.getStringCellValue());
					break;
				default:
					break;
				}
				}

			}
			scenarioList.put(list.get(0),list);
		}
		if (!(scenarioList.size() == 0)) {
			scenarioListMap.put(sheetName, scenarioList);
		}else throw new RuntimeException ("The Sheet"+ sheetName + "in the excel file is empty");
			
		
		return scenarioListMap;
	}
	
	public boolean cellContainsValue(Cell cell)
	{
		boolean flag = false;
		
		if (StringUtils.isEmpty(String.valueOf(cell)) == true || 
		        StringUtils.isWhitespace(String.valueOf(cell)) == true || 
		        StringUtils.isBlank(String.valueOf(cell)) == true || 
		        String.valueOf(cell).length() == 0 || 
		        		cell == null) {} 
		    else {
		                flag = true;
		        }
		return flag;
	}

	public Map<String, Map<String, List<String>>> getDataFromExcel(String sheetName) {
		
		String filePath = FileReaderManager.getFileReaderManager().getConfigFileReader().getScenarioExcelPath();
	    if (!cacheExcelData.containsKey(sheetName)) {

			cacheExcelData.putAll(cacheDataFromExcel(filePath, sheetName));
		}else if(cacheExcelData.containsKey(sheetName))
		{
			return cacheExcelData;
		}else
			throw new RuntimeException("Excel file reader cannot find the specified sheetname in the excel");

		return cacheExcelData;
	}
}
