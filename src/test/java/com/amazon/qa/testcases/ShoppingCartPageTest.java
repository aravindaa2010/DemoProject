package com.amazon.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.qa.base.BaseTest;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.ProductDetailsPage;
import com.amazon.qa.pages.ShoppingCartPage;
import com.amazon.qa.util.TestUtil;

//Created by 242654 - Aravindan on 23-Dec-2020
public class ShoppingCartPageTest extends BaseTest {
	
	public String classname; 
	public String methodName;

	HomePage HomeSearchpage;
	ProductDetailsPage ProductDetailsPage;
	ShoppingCartPage ShoppingCartPage;
	
	Logger log = Logger.getLogger(ShoppingCartPageTest.class);//Capture Logs
	
	public ShoppingCartPageTest() {
		super();
	}
	
	@BeforeMethod 
	public void setup() {
		initialization();
		HomeSearchpage = new HomePage();
		HomeSearchpage.searchProduct(prop.getProperty("searchkey")); //Enter Search key
		ProductDetailsPage = HomeSearchpage.searchResults(prop.getProperty("searchkey")); 	
		ProductDetailsPage.selectQuantity(Integer.parseInt(prop.getProperty("intialQuantity")));
		ProductDetailsPage.AddToCart();		
		ShoppingCartPage = ProductDetailsPage.ProceedToCart();		
	}  
	
	@BeforeMethod
	public  void  beforeMethod(Method method){
	//to get the class name in before method
	   classname = getClass().getSimpleName();
	// to get the method name in the before method 
	   methodName = method.getName();  	 	   
	}	
		
	//Test case to verify Cart page & Product added
	@Test(priority=1)
	public void verifyCartDetails() {
		String pageTitle = ShoppingCartPage.validateCartPageTitle();	    
		Assert.assertEquals(pageTitle, prop.getProperty("shoppingCartPageTitle"));	
		String productAvailable = ShoppingCartPage.verifyProductAddedToCart(prop.getProperty("searchkey"));	    
		if(productAvailable.equalsIgnoreCase("Present")){
			System.out.println("Item present in Cart");	
			log.info("Item present in the cart");
		}	
		else
		{
			System.out.println("Item not present in Cart");	
			log.error("Item not present in the cart");
		}			
	}
	
	//Test case to Update quantity and delete Product in Cart page
	@Test(priority=2)
	public void updateAndDeleteCart() {	
		ShoppingCartPage.UpdateQuantity(Integer.parseInt(prop.getProperty("FinalQuantity")));	
		ShoppingCartPage.DeleteCart();	
		String productRemoved = ShoppingCartPage.VerifyCartisEmpty();
		log.warn("Item removed from Cart");
		Assert.assertEquals(productRemoved, "Item Removed");				
	}
		
	@AfterMethod
		public void closeBrowser() throws IOException {	
		   TestUtil.takeScreenshot(methodName);
			driver.quit();
		}

}
