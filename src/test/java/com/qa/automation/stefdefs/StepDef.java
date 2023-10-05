package com.qa.automation.stefdefs;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automation.core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef {
	
	private static final Logger logger = LogManager.getLogger(StepDef.class);

	WebDriver driver;
	WebDriverWait wait;
	String baseUrl = "https://automationexercise.com/";
	int implictlyWaitTimeoutSec = 20;
	Scenario scn;
	
	@Before
	public void setUp(Scenario scn) throws Exception
	{
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		wait = new WebDriverWait(driver, implictlyWaitTimeoutSec);
	}
	
	@After
	public void cleanUp()
	{
		WebDriverFactory.quitDriver();
		scn.log("Browser got closed");
		logger.info("Browser got closed");
	}
	
	@Given("user navigate to the home application url")
	public void user_navigate_to_the_home_application_url() {
	    WebDriverFactory.navigateToTheUrl(baseUrl);
	}

	@When("application logo is displayed")
	public void application_logo_is_displayed() {
		WebElement landingPageLogoEle = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
		Assert.assertEquals(true, landingPageLogoEle.isDisplayed());
	}
	
	@Then("title of the landing page is {string}")
	public void title_of_the_landing_page_is(String landingPageTitle) {
	   Assert.assertEquals(landingPageTitle, driver.getTitle());
	}
	
	@Given("user clicks on Signup\\/login button form top header section")
	public void user_clicks_on_signup_login_button_form_top_header_section() {
		WebElement SignLoginBtnEle = driver.findElement(By.xpath("//a[text()=' Signup / Login']"));
		SignLoginBtnEle.click();
	}

	@And("user redirected to login page with title as {string}")
	public void user_redirected_to_login_page_with_title_as(String loginPageTitle) {
		wait.until(ExpectedConditions.titleContains("Automation Exercise - Signup / Login"));
		Assert.assertEquals(loginPageTitle, driver.getTitle());
	}
	@When("url for the login page contains {string} as keyword")
	public void url_for_the_login_page_contains_as_keyword(String loginPageUrlKeyword) {
		wait.until(ExpectedConditions.urlContains(loginPageUrlKeyword));
		Assert.assertEquals(true, driver.getCurrentUrl().contains(loginPageUrlKeyword));
	}
	@And("user able to see {string} section on login page")
	public void user_able_to_see_section_on_login_page(String loginPageSecHeader) {
		WebElement loginsecHeaderEle = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
		Assert.assertEquals(loginPageSecHeader, loginsecHeaderEle.getText());  
	}
	@And("user enters valid registered email id as {string}")
	public void user_enters_valid_registered_email_id_as(String userRegEmailIDtxt) {
		WebElement  loginEmailIdFieldEle = driver.findElement(By.xpath("//input[@placeholder='Email Address' and @data-qa='login-email']"));
		loginEmailIdFieldEle.sendKeys(userRegEmailIDtxt);
	}
	@And("user enters valid password as {string}")
	public void user_enters_valid_password_as(String userRegPasswordtxt) {
		WebElement  loginPasswordFieldEle = driver.findElement(By.xpath("//input[@placeholder='Password' and @data-qa='login-password']"));
		loginPasswordFieldEle.sendKeys(userRegPasswordtxt);
	}
	
	@And("click on login button")
	public void click_on_login_button() {
		WebElement LoginBtnEle = driver.findElement(By.xpath("//button[text()='Login']"));
		LoginBtnEle.click();
	}
	
	@Then("after login user able to see {string} button at top header of application")
	public void after_login_user_able_to_see_button_at_top_header_of_application(String logoutButtontext) {
		WebElement LogoutBtnEle = driver.findElement(By.xpath("//a[text()=' Logout']"));
		Assert.assertEquals(logoutButtontext, LogoutBtnEle.getText().trim());
	}
	@And("user is able to see {string} button at top header section of application")
	public void user_is_able_to_see_button_at_top_header_section_of_application(String deleteAccountButtontext) {
		WebElement DeleteBtnEle = driver.findElement(By.xpath("//a[text()=' Delete Account']"));
		Assert.assertEquals(deleteAccountButtontext, DeleteBtnEle.getText().trim());
	}

	@And("with {string} as user name just after Logged in as button")
	public void with_as_user_name_just_after_logged_in_as_button(String loggedInUserName) {
		WebElement LoggedUserNameTxtEle = driver.findElement(By.xpath("//a[text()=' Logged in as ']/b"));
		Assert.assertEquals(loggedInUserName, LoggedUserNameTxtEle.getText().trim());
	}	
		
}