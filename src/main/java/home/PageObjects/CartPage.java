package home.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import home.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents
{ 
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.cart h3")
	List<WebElement> productTitles;
	
	@FindBy(css="div.subtotal button")
	WebElement checkoutButton;
	
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = productTitles.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	public CheckoutPage goToCheckout()
	{
		checkoutButton.click();
		
		return new CheckoutPage(driver);
	}
}
