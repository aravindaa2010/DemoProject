package com.amazon.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.amazon.qa.util.TestUtil;
import com.amazon.qa.util.WebEventListener;
//Created by 242654 - Aravindan on 22-Dec-2020
public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver eventDriver;
	public static WebEventListener eventListener;
	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("/Users/A.CHANDRASEKARAN/eclipse-workspace/SeleniumTest/src/main/java"
					+ "/com/amazon/qa/config/config.properties");
			prop.load(ip);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
 public static void initialization() {
	 String browserName = prop.getProperty("browser");
	 if(browserName.equals("chrome")) {
		 System.setProperty("webdriver.chrome.driver","/Users/A.CHANDRASEKARAN/eclipse-workspace/SeleniumTest/chromedriver.exe");
		 driver= new ChromeDriver();
	 }
	 
	 eventDriver = new EventFiringWebDriver(driver);
	 //Create object EventListenerHandle
	 eventListener = new WebEventListener();
	 eventDriver.register(eventListener);
	 driver=eventDriver;
	 
	 driver.manage().window().maximize();
	 driver.manage().deleteAllCookies();
	 driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	 driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_LOAD_TIMEOUT, TimeUnit.SECONDS);
	 driver.get(prop.getProperty("url"));
 }	
	

}
