package com.cucumber.managers;

import java.io.Serializable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.browser.ChromeBrowser;
import com.cucumber.browser.FirefoxBrowser;
import com.cucumber.browser.IExploreBrowser;
import com.cucumber.enume.DriverType;

public class WebDriverManagers implements Serializable{
	
	private static WebDriver driver;
	
	public WebDriverManagers()
	{
		
	}
	
	public WebDriver getDriver()
	{
		if (driver ==  null) driver = createDriver();
		return driver;
	}
	
	private WebDriver createDriver()
	{
		WebDriver initializeDriver = null;
		if(FileReaderManager.getFileReaderManager().getConfigFileReader().getBrowser() == DriverType.CHROME)
		{
			ChromeBrowser chromeBrowser = new ChromeBrowser();
			initializeDriver =chromeBrowser.getChromeDriver(chromeBrowser.getChromeCapabilities()) ;
		}else if(FileReaderManager.getFileReaderManager().getConfigFileReader().getBrowser() == DriverType.FIREFOX)
		{
			FirefoxBrowser firefoxBrowser = new FirefoxBrowser();
			initializeDriver =firefoxBrowser.getFirefoxDriver(firefoxBrowser.getFirefoxCapabilities()) ;
		}else if(FileReaderManager.getFileReaderManager().getConfigFileReader().getBrowser() == DriverType.INTERNETEXPLORER)
		{
			IExploreBrowser IExplorerBrowser = new IExploreBrowser();
			initializeDriver =IExplorerBrowser.getIExplorerDriver(IExplorerBrowser.getIExplorerCapabilities()) ;
		}
		 return initializeDriver;
	}
	
	public void closeDriver()  
	{
		driver.close();
		driver.quit();
	}
	

}
