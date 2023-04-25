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
import home.PageObjects.OrderPage;
import home.PageObjects.ProductCatalogue;
import home.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	
	 {

		
		
		//LandingPage landingPage = launchApplication(); >> adding as @BeforeMethod
		
		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));

		// ProductCatalogue productCatalogue = new ProductCatalogue(driver); >> skip
		// this line as we found new way to reduce overhead of creating objects for each
		// pages.

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("productName"));

		CartPage cartPage = productCatalogue.goToCartPage();

		// CartPage cartPage = new CartPage(driver);

		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));

		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("India");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String endText = confirmationPage.getConfirmationMessage();

		Assert.assertEquals(endText, "THANKYOU FOR THE ORDER.");
	}
	
	
	// run this test only after executing above testcase
	// verify 
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApp("dipogi3573@klblogs.com", "Test@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	
	}
	
	/*
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		File file = new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports"+testCaseName+".png"; 
	}
	*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\home\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	/* Method 2 - way to use DataProdvide
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email","dipogi3573@klblogs.com");
		map.put("password","Test@123");
		map.put("productName","ADIDAS ORIGINAL");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email","fanoke8359@kuvasin.com");
		map2.put("password","Test@123");
		map2.put("productName","ZARA COAT 3");
		
		return new Object[][] {{map},{map2}};
	}
	*/
	
	/* Method 1 - alternate but simple way to use DataProvider
	@DataProvider
	public Object[][] getData()
	{
		
		
		return new Object[][] {{"dipogi3573@klblogs.com","Test@123","ADIDAS ORIGINAL"},{"fanoke8359@kuvasin.com","Test@123","ZARA COAT 3"}};
	}
	*/
	
	
}
