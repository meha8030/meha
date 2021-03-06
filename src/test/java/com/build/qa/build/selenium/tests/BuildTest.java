package com.build.qa.build.selenium.tests;

import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import com.build.qa.build.selenium.pageobjects.homepage.ShoppingCart;

import org.junit.Assert;

public class BuildTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic functionality and page objects
	 * as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onBuildTheme()).as("The website should load up with the Build.com desktop theme.")
				.isTrue();
	}

	/**
	 * Search for the Quoizel MY1613 from the search bar
	 * 
	 * @assert: That the product page we land on is what is expected by checking the
	 *          product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		String title = homePage.searchPage("Quoizel MY1613");
		Assert.assertEquals("Quoizel MY1613", title);
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.build.com/bathroom-sinks/c108504) and add the second product on
	 * the search results (Category Drop) page to the cart.
	 * 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */

	@Test
	public void addProductToCartFromCategoryDrop() {
		driver.get(getConfiguration("BATHSINKS"));
		ShoppingCart cartPage = new ShoppingCart(driver, wait);
		String productName = cartPage.selectAndVerifyBathSinkProduct();
		Assert.assertEquals("Miseno", productName);
	}

	/**
	 * Add a product to the cart and email the cart to yourself, also to my email
	 * address: test.automation+SeleniumTest@build.com Include this message in the
	 * "message field" of the email form: "This is {yourName}, sending you a cart
	 * from my automation!"
	 * 
	 * @assert that the "Cart Sent" success message is displayed after emailing the
	 *         cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() {
		driver.get(getConfiguration("BATHSINKS"));
		ShoppingCart cartPage = new ShoppingCart(driver, wait);
		String receiverEmail = "test.automation+SeleniumTest@build.com";
		String message = cartPage.emailCart(receiverEmail);
		Assert.assertEquals("Cart Sent! The cart has been submitted to the recipient via email", message);

	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by at least
	 * two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * 
	 * @assert that the correct filters are being narrowed, and the result count is
	 *         correct, such that each facet selection is narrowing the product
	 *         count.
	 * @difficulty Hard
	 */

	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		driver.get(getConfiguration("BATHSINKS"));
		ShoppingCart verifyFilters = new ShoppingCart(driver, wait);
		Assert.assertTrue(verifyFilters.validateResultsCount());
	}
}
