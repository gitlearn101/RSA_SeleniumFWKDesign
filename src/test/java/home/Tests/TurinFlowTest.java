package home.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TurinFlowTest {

	
	@Test
	public void amazonRun() throws InterruptedException {
	
	WebDriverManager.chromedriver().setup(); // automatically pull browser driver as per our browser version.
	
	//WebDriver driver = new ChromeDriver();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	ChromeDriver driver = new ChromeDriver(options);
	
	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.get("https://www.google.com/");
	
	driver.findElement(By.id("APjFqb")).sendKeys("Turing");
	driver.findElement(By.id("APjFqb")).sendKeys(Keys.ENTER);
	
	// click on first url
	driver.findElement(By.className("eKjLze")).click();
	
	// turin screen
	
	/*
	// Create an account
	driver.findElement(By.xpath("//*[@id='LoginButton__button']")).click();
	driver.findElement(By.xpath("//*[@id='LoginButton__button']/following-sibling::div//div//a/following-sibling::a[@id='DeveloperSignup']")).click();
	*/
	
	driver.findElement(By.xpath("//*[@id='LoginButton__button']")).click();
	driver.findElement(By.xpath("//*[@id='LoginButton__button']/following-sibling::div//a[@id='DeveloperLogin']")).click();
	
	// input cred for login
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("defejop676@larland.com");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	// click on 'skip' at the footer
	driver.findElement(By.cssSelector(".pretest-skip-resume-link")).click();
	
	}
	
}
