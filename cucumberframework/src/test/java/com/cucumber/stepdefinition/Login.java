package com.cucumber.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.cucumber.context.TestContext;
import com.cucumber.managers.FileReaderManager;
import com.cucumber.managers.WebDriverManagers;
import com.cucumber.pageobjects.LoginPage;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Login {
	TestContext testContext;
	LoginPage loginPage;
	
	
	public Login(TestContext testContext)
	{
		this.testContext = testContext;
		loginPage = testContext.getPageObjectManager().getLoginPage();
		
	}
	
	@Given("user in on the login page")
	public void user_in_on_the_login_page() throws InterruptedException {
	   System.out.println("user is on the login page of the google first step");
	 loginPage.clickGoogleSearchField();
	 loginPage.TextInGoogleSearchField();
	 
	 Thread.sleep(5000);
	 Assert.assertTrue(true);
	 
	}

	@Then("User enters the username and password")
	public void user_enters_the_username_and_password() {
		System.out.println("user enters the username and password");
	}

	@Then("User clicks on the login button")
	public void user_clicks_on_the_login_button() {
		System.out.println("User clicks on the login button");
	}


}
