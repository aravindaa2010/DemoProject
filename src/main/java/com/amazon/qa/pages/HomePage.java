package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.BaseTest;

//Created by 242654 - Aravindan on 22-Dec-2020
public class HomePage extends BaseTest {
	
	//Define the Page Elements
	
	//Amazon Logo image
	@FindBy(id="nav-logo-sprites")
	WebElement pagelogo;	
		
	//Search box
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBar;
	
	//Click Search Icon
	@FindBy(xpath="//input[@value='Go']")
	WebElement searchGoIcon;	
	
	//Search Results
	@FindBy(xpath="//a[@class='a-link-normal a-text-normal'][1]")	
	WebElement searchResult;	
	////*[@id="CardInstanceGMhzwk3Jx7eajP4wkk-eYQ"]/div[1]/div/div[2]/div[1]/div/div/div[2]/a/span/span[2]
			
	//Initializing the Page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);		
	}
	
	//Actions
	//Get Page title
	public String validateHomePageTitle()
	{
		return driver.getTitle();
	}
	
	//Verify logo is displayed
	public boolean validateAmazonLogo()
	{
		return pagelogo.isDisplayed();
	}
	
	//Search for Product
	public void searchProduct(String itemToSearch) {
		System.out.println(prop.getProperty(itemToSearch));
		searchBar.sendKeys(itemToSearch);
		searchGoIcon.click();		
		
	}
	
	//Verify if Search results matches the search keyword and click on the link if True
	public ProductDetailsPage searchResults(String itemToSearch) {		
		String actualText = searchResult.getText();		
		if (actualText.contains(itemToSearch)){
		searchResult.click();
		}
		return new ProductDetailsPage();
		
	}
	
}
