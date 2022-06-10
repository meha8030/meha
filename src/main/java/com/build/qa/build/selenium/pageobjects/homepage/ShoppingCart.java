package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ShoppingCart extends BasePage {
	private By buildThemeBody;

	public ShoppingCart(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
		PageFactory.initElements(driver, this);
	}

	public boolean onBuildTheme() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}

	@FindBy(xpath = "//div[@data-automation='product-card-mno1812ru']")
	WebElement bathSinkProduct;

	@FindBy(xpath = "//span[text()='Add to Cart']")
	WebElement addToCart;

	@FindBy(xpath = "//a[@data-automation='header-cart-link']")
	WebElement openCart;

	@FindBy(xpath = "//span[text()='Miseno' and @class='fw6']")
	WebElement cardProduct;

	@FindBy(xpath = "//div[@class='relative']/div[@role='button']")
	WebElement homePopup;

	@FindBy(xpath = "//div[text()='Email Cart']")
	WebElement emailCart;

	@FindBy(xpath = "//span[text()='Cart Tools']")
	WebElement cardTools;

	@FindBy(xpath = "//input[@name='yourName']")
	WebElement yourName;

	@FindBy(xpath = "//input[@name='yourEmail']")
	WebElement yourEmail;

	@FindBy(xpath = "//input[@name='recipientName']")
	WebElement recipientName;
	
	@FindBy(xpath = "//input[@name='recipientEmail']")
	WebElement recipientEmail;

	@FindBy(xpath = "//button[text()='Email Cart']")
	WebElement sendEmail;
	
	@FindBy(xpath = "//span[contains(text(),'Cart Sent! The cart has been submitted to the recipient via email')]")
	WebElement confMsg;
	
	@FindBy(xpath="//h1[text()='Fast and Free Shipping']")
	WebElement fastNfreeShipping;
	
	@FindBy(xpath = "//ul[@data-automation='facet-range'][1]")
	WebElement chkFastnFreeShipping;
	
	@FindBy(xpath = "//h1[text()='In Stock']")
	WebElement inStock;
	
	@FindBy(xpath = "//ul[@data-automation='facet-range'][2]")
	WebElement chkInStock;
	
	@FindBy(xpath = "//div[@data-automation='results-count']")
	WebElement lblResultCount;
	
	int extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", "");
  
        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();
  
        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");
        
        return Integer.valueOf(str);
    }

	public Boolean validateResultsCount() {
		Boolean decrStockCount = false;
		int currCount = 0;
		currCount = extractInt(lblResultCount.getText());
		fastNfreeShipping.click();
		chkFastnFreeShipping.click();
		
		if (currCount > extractInt(lblResultCount.getText())) {
			currCount = extractInt(lblResultCount.getText());
			decrStockCount = true;
		}
		inStock.click();
		chkInStock.click();
		if (currCount > extractInt(lblResultCount.getText())) {
			currCount = extractInt(lblResultCount.getText());
			decrStockCount = true;
		}		
		return decrStockCount;
	}
	
	public String emailCart(String recipientEmailID) {
		homePopup.click();
		bathSinkProduct.click();
		addToCart.click();
		cardTools.click();
		emailCart.click();
		yourName.sendKeys("John");
		yourEmail.sendKeys("Test1@gmail.com");
		recipientName.sendKeys("Smith");
		recipientEmail.sendKeys(recipientEmailID);
		sendEmail.click();
		return confMsg.getText();

	}

	public String selectAndVerifyBathSinkProduct() {
		homePopup.click();
		bathSinkProduct.click();
		addToCart.click();
		return cardProduct.getText();
	}
}
