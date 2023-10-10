package com.qa.automation.pageobject;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPageObjects {

	private static final Logger logger = LogManager.getLogger(ProductsPageObjects.class);

	private WebDriver driver;
	private WebDriverWait wait;
	int i;

	
	//parametrized the constructor
	public ProductsPageObjects(WebDriver driver)
	{
		this.driver = driver;
        wait = new WebDriverWait(driver, 15);

	}
	
	//Page Locators
    private By productsearchBox = By.xpath("//input[@id='search_product']");
    private By productsearchBtn = By.xpath("//button[@id='submit_search']");
    private By searchProdList = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']/p");
    private By actBrandCategory = By.xpath("//div[@id='accordian']//div[@class='panel-heading']//a");

	
	//Page Methods
	public String productsPageUrl()
	{
		//return "/products";
		
			//or
		String prodPageUrl = "/products";
		return prodPageUrl;
	}
	
	public void validateProdPageTitle(String pageTitle)
	{
		wait.until(ExpectedConditions.titleContains(pageTitle));
		Assert.assertEquals(pageTitle, driver.getTitle());
	}
	
	public void validatePageUrlKeyword(String keywordInUrl)
	{
		wait.until(ExpectedConditions.urlContains(keywordInUrl));
		Assert.assertEquals(true, driver.getCurrentUrl().contains(keywordInUrl));
	}
	
	public void searchForProd(String productName )
	{
		WebElement productsearchBoxEle = driver.findElement(productsearchBox);
		productsearchBoxEle.sendKeys(productName);
	}
	
	public void clickOnSearchBtn()
	{
		WebElement productsearchBtnEle = driver.findElement(productsearchBtn);
		productsearchBtnEle.click();
	}
	
	public void ValproductsListFirstProdName(String productNameKeyword)
	{
		List<WebElement> searchProdListEle = driver.findElements(searchProdList);
		Assert.assertEquals(true, searchProdListEle.get(0).getText().contains(productNameKeyword));
	}
	
	public void validateCategoryList(List<String> brandCategoryNameList)
	{
		List<String> expectedbrandCategoryNameList = brandCategoryNameList;
		List<WebElement> actBrandCategoryListEle = driver.findElements(actBrandCategory);
		for(int i = 0; i < expectedbrandCategoryNameList.size(); i++);
		{
			System.out.println(actBrandCategoryListEle.get(i).getText());	
		}
	}
}
