package home.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import home.PageObjects.CartPage;
import home.PageObjects.CheckoutPage;
import home.PageObjects.ConfirmationPage;
import home.PageObjects.ProductCatalogue;
import home.TestComponent.BaseTest;
import home.TestComponent.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException
	
	 {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApp("dipogi3573@klblogs.com", "Test@");
		
		Assert.assertEquals("Incorrect  password.", landingPage.getErrorMessage());
		
		
		// correct >> Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	
	
	@Test
	public void ProductErrorValidation() throws InterruptedException
	
	 {

			String productName = "ADIDAS ORIGINAL";
			
			//LandingPage landingPage = launchApplication(); >> adding as @BeforeMethod
			
			ProductCatalogue productCatalogue = landingPage.loginApp("dipogi3573@klblogs.com", "Test@123");

			// ProductCatalogue productCatalogue = new ProductCatalogue(driver); >> skip
			// this line as we found new way to reduce overhead of creating objects for each
			// pages.

			List<WebElement> products = productCatalogue.getProductList();

			productCatalogue.addProductToCart(productName);

			CartPage cartPage = productCatalogue.goToCartPage();

			// CartPage cartPage = new CartPage(driver);

			Boolean match = cartPage.verifyProductDisplay(productName);

			Assert.assertTrue(match);

			CheckoutPage checkoutPage = cartPage.goToCheckout();

			checkoutPage.selectCountry("India");

			ConfirmationPage confirmationPage = checkoutPage.submitOrder();

			String endText = confirmationPage.getConfirmationMessage();

			Assert.assertEquals(endText, "THANKYOU FOR THE ORDER.");
		}

}
