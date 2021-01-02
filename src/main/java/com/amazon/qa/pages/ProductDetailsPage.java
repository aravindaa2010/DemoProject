package com.amazon.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.qa.base.BaseTest;
import com.amazon.qa.pages.HomePage;

//Created by 242654 - Aravindan on 23-Dec-2020
public class ProductDetailsPage extends BaseTest{	
	
	//Get the Product name
	@FindBy(id="productTitle")
	WebElement productTitle;
	
	//Get the no of Reviews
	@FindBy(xpath="//*[contains(@id,'acrCustomerReviewText')]")
	WebElement productReviews;
	
	//Get the price
	@FindBy(id="price_inside_buybox")
	//@FindBy(id="priceblock_ourprice")	
	WebElement productPrice;
	
	//select quantity
	Select QuantitySelect = new Select(driver.findElement(By.xpath("//*[@name='quantity']")));
	
	//Add to Cart button
	@FindBy(xpath="//input[contains(@name,'submit.add-to-cart')]")
	WebElement AddToCart;
	
	//Proceed to Cart
	@FindBy(xpath="//a[contains(@id,'hlb-view-cart-announce')]")
	WebElement ProceedToCart;
	
	
	//Initializing the Page Objects
		public ProductDetailsPage() {
			PageFactory.initElements(driver, this);		
		}
	
	//Method to get product details
	public void getProductTitle() {		
		String ProdName = productTitle.getText();
		System.out.println("productTitle: "+ProdName);		
	}
	
	//Method to get product Reviews
	public void getProductRating() {		
		String ProdRating = productReviews.getText();
		System.out.println("No. of Product Reviews: "+ProdRating);		
	}
	
	//Method to get product price
	public void getProductPrice() {		
		String prodPrice = productPrice.getText();
		System.out.println("Price: "+prodPrice);		
	}
	
	public void selectQuantity(int productquantity) {		
			QuantitySelect.selectByIndex(productquantity);
	}
	
	public void AddToCart() {
			if(AddToCart.isDisplayed()) {				
				AddToCart.click();						
			}	
	}
	
	public ShoppingCartPage ProceedToCart() {
		if(ProceedToCart.isDisplayed()) {
			System.out.println("Add to Cart displayed");
			ProceedToCart.click();						
		}
		return new ShoppingCartPage() ;	
}
		
}
