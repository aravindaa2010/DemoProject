package com.amazon.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.qa.base.BaseTest;

//Created by 242654 - Aravindan on 22-Dec-2020
public class ShoppingCartPage extends BaseTest {	
	
	//Proceed to Checkout	
		@FindBy(xpath="//input[contains(@name,'proceedToRetailCheckout')]")
		WebElement proceedToCheckout;
		
	//Delete Cart Items
		@FindBy(xpath="//input[contains(@value,'Delete')]")
		WebElement deleteCart;	
		
	//Verify cart is empty
		@FindBy(xpath="//div[contains(@class,'amazon-cart-is-empty')]")
		WebElement emptyCart;
	
	//Update quantity
	   Select QuantityUpdate = new Select(driver.findElement(By.xpath("//*[@name='quantity']")));	
	
		
	//Initializing the Page Objects
		public ShoppingCartPage() {
			PageFactory.initElements(driver, this);		
		}
	
	//Get Page title
	public String validateCartPageTitle()
	{
		return driver.getTitle();
	}
	
	//Verify Product is available in cart
		public String verifyProductAddedToCart(String productName)
		{
			if(driver.getPageSource().contains(productName)){
				return "Present";				
			}
			else {
				return "Absent";
			}
		}
		
	public void ProceedToCheckout() {
		if(proceedToCheckout.isEnabled()) {
			
			proceedToCheckout.click();
		}
		
	}	
	
	public void DeleteCart() {
		if(deleteCart.isDisplayed()) {
			deleteCart.click();
		}
		
	}
	
	
	public String VerifyCartisEmpty() {
		if(emptyCart.isDisplayed()) {
			return "Item Removed";				
		}
		else {
			return "Item not Removed";		
		}
		
	}
	
	/* public String VerifyCartisEmpty(String emptyMsg) {
		if(driver.getPageSource().contains(emptyMsg)){
			return "Item Removed";				
		}
		else {
			return "Item not Removed";		
		}
		
	} */
	
	public void UpdateQuantity(int productquantity) {		
		QuantityUpdate.selectByIndex(productquantity);
}
}
