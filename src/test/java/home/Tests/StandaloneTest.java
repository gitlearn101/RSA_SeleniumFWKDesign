package home.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		String productName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup(); // automatically pull browser driver as per our browser version.
		
		//WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		
		
		// input cred in login screen and submit login 
		driver.findElement(By.id("userEmail")).sendKeys("dipogi3573@klblogs.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		
		
		// collect list of all products available in e-comm
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
		
		// Task - to click on 'add to cart' button of a particular product ie ADIDAS ORIGINAL
		
		// Step1 : iterate through all product catalogue using stream() 
		
		WebElement prod =  products.stream().filter(p->p.findElement(By.cssSelector("h5")).getText().equals(productName)).findFirst().orElse(null);
		
		// Step2 : click on 'add to cart' of ADIDAS of above stream()
		prod.findElement(By.cssSelector("div.card-body button:last-of-type")).click();
		
		
		// Wait till toaster message appear annd loader disappear
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#toast-container")));
		
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); // This is un-optimise

		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // This is optimised
		Thread.sleep(3000);
		// click on caart icon.
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// Capture all products present in cart.
		List<WebElement> cartProduct = driver.findElements(By.cssSelector("div.cart h3"));
		
		// We are matching our product "Adiddas" is present in the cart
		Boolean matcher = cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		
		// if the matcher -> True , then Assert will pass
		Assert.assertTrue(matcher); 
		
		// click on 'checkout' button
		driver.findElement(By.cssSelector("div.subtotal button")).click();
		
		// add details to a textfield using Actions Class
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("INDIA");
		
		// wait for dropdown box to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ta-item")));
		
		// Select India (second options from the dropdown)
		driver.findElement(By.cssSelector("button.ta-item:nth-of-type(2)")).click();
		
		// click on 'place order' button
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		// validate last screen by confirming text
		String endText= driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		
		Assert.assertEquals(endText, "THANKYOU FOR THE ORDER.");
		
		
		
	}

}
