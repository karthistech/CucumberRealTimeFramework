package com.cucumber.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input") 
	 private WebElement GoogleSearchField;
	
	public void clickGoogleSearchField()
	{
		GoogleSearchField.click();
	}
	
	public void TextInGoogleSearchField()
	{
		GoogleSearchField.sendKeys("This is my First Test");
	}
}
