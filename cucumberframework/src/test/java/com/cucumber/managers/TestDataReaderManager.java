package com.cucumber.managers;

import com.cucumber.testdata.TestDataProvider;

public class TestDataReaderManager {
	
	TestDataProvider testDataProvider;
	
	public TestDataReaderManager()
	{
		this.testDataProvider = new TestDataProvider();
		
	}
	
	public TestDataProvider getTestDataProvider()
	{
		return testDataProvider;
	}

}
