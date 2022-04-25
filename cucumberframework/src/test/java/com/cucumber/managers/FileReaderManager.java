package com.cucumber.managers;

import com.cucumber.utilities.ConfigFileReader;

public class FileReaderManager {
	
	private ConfigFileReader configFileReader;
	private static FileReaderManager fileReaderManager;
	
	private FileReaderManager()
	{
		configFileReader = ConfigFileReader.getConfigFileReaderInstance();
	}

	public static FileReaderManager getFileReaderManager()
	{
		if (fileReaderManager == null) fileReaderManager = new FileReaderManager();
		
		return fileReaderManager;
	}
	
	public ConfigFileReader getConfigFileReader()
	{
		return configFileReader;
	}
}
