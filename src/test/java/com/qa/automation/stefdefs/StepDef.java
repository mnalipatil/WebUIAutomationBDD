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
	int i;
	
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
	
	@After(order=1)
	public void cleanUp()
	{
		WebDriverFactory.quitDriver();
		scn.log("Browser got closed");
		logger.info("Browser got closed");
	}
	
	@After(order=2)
	public void takeScreenShot(Scenario s)
	{
		if(s.isFailed())
		{
			TakesScreenshot scrnShot = (TakesScreenshot)driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Failed Step Name: " + s.getName());
		}
		else
		{
			scn.log("Test case is passed, no screen shot captured");
		}
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
	
	@When("user header over to products page")
	public void user_header_over_to_product_page()
	{
		driver.navigate().to("https://automationexercise.com/products");
	}

	@When("user redirected to products page with title as {string}")
	public void user_redirected_to_products_page_with_title_as(String pageTitle)
	{
		wait.until(ExpectedConditions.titleContains(pageTitle));
		Assert.assertEquals(pageTitle, driver.getTitle());
	}
	
	@When("url page login page containts the {string} as keyword")
	public void url_for_the_login_page_contains_the_as_keyword( String keywordInUrl)
	{
		wait.until(ExpectedConditions.urlContains(keywordInUrl));
		Assert.assertEquals(true, driver.getCurrentUrl().contains(keywordInUrl));
	}
	
	@When("user search for a product {string}")
	public void user_search_for_a_product(String productName)
	{
		WebElement productsearchBoxEle = driver.findElement(By.xpath("//input[@id='search_product']"));
		productsearchBoxEle.sendKeys(productName);
	}
	
	@When("click on search button")
	public void click_on_search_button()
	{
		WebElement productsearchBtnEle = driver.findElement(By.xpath("//button[@id='submit_search']"));
		productsearchBtnEle.click();
	}
	
	@Then("form the product list the first product contains the {string} as keyword")
	public void form_the_product_list_the_first_product_contains_the_as_keyword(String productNameKeyword)
	{
		List<WebElement> searchProdListEle = driver.findElements(By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p"));
		Assert.assertEquals(true, searchProdListEle.get(0).getText().contains(productNameKeyword));
	}
	
	@When("User is able to see {string} header")
	public void user_is_able_to_see_header(String categoryStringValue) {
		WebElement categoryStringEle = driver.findElement(By.xpath("//h2[text()='Category'] "));
		Assert.assertEquals(categoryStringValue, categoryStringEle.getText());
	}

	@Then("Under category below list is displayed")
	public void under_category_below_list_is_displayed(List<String> brandCategoryNameList) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		List<String> expectedbrandCategoryNameList = brandCategoryNameList;
		List<WebElement> actBrandCategoryListEle = driver.findElements(By.xpath("//div[@id='accordian']//div[@class='panel-heading']//a"));
		for(int i = 0; i < expectedbrandCategoryNameList.size(); i++);
		{
			System.out.println(actBrandCategoryListEle.get(i).getText());	
		}
}
}