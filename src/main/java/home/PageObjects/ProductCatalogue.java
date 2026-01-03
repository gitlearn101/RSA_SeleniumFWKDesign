package home.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import home.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//  List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
	
	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector("div.mb-3");
	By addToCart = By.cssSelector("div.card-body button:last-of-type");
	By toastMessage = By.cssSelector("div#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod =  getProductList().stream().filter(p->p.findElement(By.cssSelector("h5")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	// As discussed by the Instructors, we have to hide exp/imp wait as the application is not working properly in backend.
	// hence using thread.sleep as extreme workaround
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		Thread.sleep(2000);
		//waitForElementToAppear(toastMessage);
		//waitForElementToDisappear(spinner);
	}

}
