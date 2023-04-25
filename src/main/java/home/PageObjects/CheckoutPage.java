package home.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import home.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents
{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="button.ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector("button.ta-item");
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		
		return new ConfirmationPage(driver);
	}
	
}
