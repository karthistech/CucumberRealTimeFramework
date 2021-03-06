package com.cucumber.browser;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cucumber.managers.FileReaderManager;

/**
 * @author rsr
 *
 * Aug 6, 2016
 */
public class IExploreBrowser implements Serializable{
	
	public Capabilities getIExplorerCapabilities() {
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,
				ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		cap.setJavascriptEnabled(true);
		return cap;
	}
	
	public WebDriver getIExplorerDriver(Capabilities cap) {
		System.setProperty("webdriver.ie.driver", FileReaderManager.getFileReaderManager().getConfigFileReader().getDriverPath());
//		System.setProperty("webdriver.ie.driver.logfile", ResourceHelper.getResourcePath("logs/iexplorerlogs/") + "ielog" + DateTimeHelper.getCurrentDateTime() + ".log");
		return new InternetExplorerDriver(cap);
	}
	
	public WebDriver getIExplorerDriver(String hubUrl,Capabilities cap) throws MalformedURLException {
		return new RemoteWebDriver(new URL(hubUrl), cap);
	}

}