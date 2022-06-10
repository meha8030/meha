package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class HomePage extends BasePage {

	private By buildThemeBody;

	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
		PageFactory.initElements(driver, this);
	}

	public boolean onBuildTheme() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
	
	@FindBy(xpath = "//div[@class='modal-dialog   ']//button[1]")
	public WebElement modelPopup;
	
	@FindBy(xpath = "//input[@id='search_txt']")
	public WebElement searchProduct;
	


	public String searchPage(String product) {
		modelPopup.click();
		searchProduct.click();
		searchProduct.sendKeys(product);
		return driver.getTitle();
	}

}
