package home.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import home.PageObjects.CartPage;
import home.PageObjects.CheckoutPage;
import home.PageObjects.ConfirmationPage;
import home.PageObjects.LandingPage;
import home.PageObjects.OrderPage;
import home.PageObjects.ProductCatalogue;
import home.PageObjects.RegistrationPage;
import home.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	// Product name to be used in the test
	String productName = "ADIDAS ORIGINAL";

	/**
	 * Test to submit an order.
	 * This test performs the following steps:
	 * 1. Registers a new user using data from the DataProvider.
	 * 2. Logs in with the registered user credentials.
	 * 3. Adds a product to the cart.
	 * 4. Verifies the product is displayed in the cart.
	 * 5. Proceeds to checkout and submits the order.
	 * 6. Verifies the confirmation message.
	 *
	 * @param input HashMap containing test data (e.g., user details, product name).
	 */
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// Step 1: Register a new user
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.performRegistration(
				input.get("firstName"),
				input.get("lastName"),
				input.get("email"),
				input.get("phone"),
				input.get("occupation"),
				input.get("gender"),
				input.get("password"),
				input.get("confirmPassword")
		);

		// Step 2: Log in with the registered user credentials
		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));

		// Step 3: Get the list of products and add the specified product to the cart
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));

		// Step 4: Navigate to the cart page and verify the product is displayed
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match, "Product not found in the cart!");

		// Step 5: Proceed to checkout and submit the order
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		// Step 6: Verify the confirmation message
		String endText = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(endText, "THANKYOU FOR THE ORDER.", "Order confirmation message mismatch!");
	}

	/**
	 * Test to verify the order history.
	 * This test depends on the `submitOrder` test and performs the following steps:
	 * 1. Logs in with a predefined user.
	 * 2. Navigates to the orders page.
	 * 3. Verifies the product is displayed in the order history.
	 */
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		// Log in with predefined credentials
		ProductCatalogue productCatalogue = landingPage.loginApp("dipogi3573@klblogs.com", "Test@123");

		// Navigate to the orders page
		OrderPage orderPage = productCatalogue.goToOrdersPage();

		// Verify the product is displayed in the order history
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName), "Product not found in order history!");
	}

	/**
	 * DataProvider to supply test data for the tests.
	 * Reads data from a JSON file and converts it into a list of HashMaps.
	 *
	 * @return Object[][] containing test data.
	 * @throws IOException if the JSON file cannot be read.
	 */
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\home\\data\\PurchaseOrder.json");
		return new Object[][]{{data.get(0)}, {data.get(1)}};
	}
}