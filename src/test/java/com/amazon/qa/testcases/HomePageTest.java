package com.amazon.qa.testcases;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.amazon.qa.base.BaseTest;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.ProductDetailsPage;
import com.amazon.qa.util.TestUtil;


//Created by 242654 - Aravindan on 22-Dec-2020
public class HomePageTest extends BaseTest {
	
	public String classname; 
	public String methodName;
	HomePage HomeSearchpage;
	ProductDetailsPage ProductDetailsPage;
	
	Logger log = Logger.getLogger(HomePageTest.class); // To capture runtime logs
	
	public HomePageTest() {
		super();
	}	
		
	@BeforeMethod 
	public void setup() {
		initialization();
		HomeSearchpage = new HomePage();		
	}
	

	@BeforeMethod
	public  void  beforeMethod(Method method){
	//to get the class name in before method
	   classname = getClass().getSimpleName();
	// to get the method name in the before method 
	   methodName = method.getName();  
	}
	
	@Test(priority=1)
	public void VerifyTitle() {
		log.info("Home Page Opened");
		String pageTitle = HomeSearchpage.validateHomePageTitle();	    
		Assert.assertEquals(pageTitle, prop.getProperty("HomePagetitle"));
		log.info("Test Case executed");
		
	}
	
	@Test(priority=2)
	public void verifyLogo() {
		boolean Flag = HomeSearchpage.validateAmazonLogo();
		Assert.assertTrue(Flag);
	}
	
	@Test(priority=3)
	public void searchProduct() {
		System.out.println(prop.getProperty("searchkey"));
		HomeSearchpage.searchProduct(prop.getProperty("searchkey"));
	}
	
   @Test(priority=4)
	public void VerifySearchResults() {
		System.out.println(prop.getProperty("searchkey"));
		HomeSearchpage.searchProduct(prop.getProperty("searchkey")); //Enter Search key
		ProductDetailsPage = HomeSearchpage.searchResults(prop.getProperty("searchkey")); //Verify Search results
	}
	
	
   @AfterMethod
	public void closeBrowser() throws IOException {
	   TestUtil.takeScreenshot(methodName);
	   log.info("Test Case completed");
		driver.quit();
		
	}

}
