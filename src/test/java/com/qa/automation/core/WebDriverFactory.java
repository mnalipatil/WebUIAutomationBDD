package com.qa.automation.core;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver=null;
    
    public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
        switch(browser.toLowerCase()){
            case "chrome":
            	System.setProperty("webdriver.chrome.driver","C:\\Users\\Manali Patil\\eclipse-workspace\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
        		logger.info("Setting up for browser path is completed");
        		ChromeOptions opt = new ChromeOptions();
        		opt.setBinary("C:\\Users\\Manali Patil\\eclipse-workspace\\chrome-win64 (2)\\chrome-win64\\chrome.exe");
        		logger.info("Setting up chrome binary completed");
        		driver = new ChromeDriver(opt);
                logger.info("Chrome Browser invoked"); 
        		
                break;
            case "firefox":
            	WebDriverManager.firefoxdriver().setup();
            	driver = new FirefoxDriver();
                logger.info("Firefox Browser invoked");
                break;
            case "opera":
            	WebDriverManager.operadriver().setup();
            	driver = new OperaDriver();
                logger.info("Opera Browser invoked");
                break;
            case "edge":
            	WebDriverManager.edgedriver().setup();
            	driver = new EdgeDriver();
                logger.info("Opera Browser invoked");
                break;
            case "headless":
            	System.setProperty("webdriver.chrome.driver","C:\\Users\\Manali Patil\\eclipse-workspace\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
        		logger.info("Setting up for browser path is completed");
        		ChromeOptions options = new ChromeOptions();
        		options.setBinary("C:\\Users\\Manali Patil\\eclipse-workspace\\chrome-win64 (2)\\chrome-win64\\chrome.exe");
        		logger.info("Setting up chrome binary completed");
        		options.addArguments("headless");
        		options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);
                logger.info("Headless Chrome Browser invoked");
                break;
            default:
                logger.fatal("No such browser is implemented.Browser name sent: " + browser);
                throw new Exception("No such browser is implemented.Browser name sent: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        logger.info("Driver maximized and implicit time out set to 20 seconds");
        return driver;
    }

    public static void navigateToTheUrl(String url){
        driver.get(url);
        logger.info("Browser navigated to the url: " + url);
    }

    public static void quitDriver(){
        driver.quit();
        logger.info("Driver closed");
    }

    public static String getBrowserName(){
        String browserDefault = "chrome"; //Set by default
        String browserSentFromCmd = System.getProperty("browser");

        if (browserSentFromCmd==null){
            return browserDefault;
        }else{
            return browserSentFromCmd;
        }
    }

}

