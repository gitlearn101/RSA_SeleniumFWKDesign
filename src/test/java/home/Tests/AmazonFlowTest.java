package home.Tests;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonFlowTest {

	
	@Test
	public void amazonRun() {
	
	WebDriverManager.chromedriver().setup(); // automatically pull browser driver as per our browser version.
	
	//WebDriver driver = new ChromeDriver();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	ChromeDriver driver = new ChromeDriver(options);
	
	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.get("https://rahulshettyacademy.com/client/");
	
	}
	
}
