package com.amazon.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.amazon.qa.base.BaseTest;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.ProductDetailsPage;
import com.amazon.qa.pages.ShoppingCartPage;
import com.amazon.qa.util.TestUtil;
//Created by 242654 - Aravindan on 23-Dec-2020
public class ProductDetailsPageTest extends BaseTest {
	
	public String classname; 
	public String methodName;
	HomePage HomeSearchpage;
	ProductDetailsPage ProductDetailsPage;
	ShoppingCartPage ShoppingCartPage;
	
	public ProductDetailsPageTest() {
		super();
	}
	
	@BeforeMethod 
	public void setup() {
		initialization();
		HomeSearchpage = new HomePage();
		HomeSearchpage.searchProduct(prop.getProperty("searchkey")); //Enter Search key
		ProductDetailsPage = HomeSearchpage.searchResults(prop.getProperty("searchkey")); 		
	}
	
	@BeforeMethod
	public  void  beforeMethod(Method method){
	//to get the class name in before method
	   classname = getClass().getSimpleName();
	// to get the method name in the before method 
	   methodName = method.getName();  
	}
	
	@Test(priority=1)
	public void GetProductDetails() {		
		ProductDetailsPage.getProductTitle();	
		ProductDetailsPage.getProductRating();	
		ProductDetailsPage.getProductPrice();
	}	
	
	@Test(priority=2)
	public void AddToCart() {
		ProductDetailsPage.selectQuantity(Integer.parseInt(prop.getProperty("intialQuantity")));
		ProductDetailsPage.AddToCart();		
		ShoppingCartPage = ProductDetailsPage.ProceedToCart();
	}
	
  @AfterMethod
	public void closeBrowser() throws IOException {
	  TestUtil.takeScreenshot(methodName);
	  driver.quit();
	}


}
