package home.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import home.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents
{ 
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNameTitle;
	
	@FindBy(css="div.subtotal button")
	WebElement checkoutButton;
	
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match = productNameTitle.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
