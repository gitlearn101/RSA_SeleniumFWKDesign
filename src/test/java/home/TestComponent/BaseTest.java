package home.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import home.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest
{
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//home//resources//GlobalData.properties");
		prop.load(fis);

		// Read value coming from CMD terminal (using concept of Java ternary operator)
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");

		// prop.getProperty("browser"); -> instead of reading from GlobalData.properties file; we will read from cmd terminal/prop based on runtime.

		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup(); // automatically pull browser driver as per our browser version.

			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);

			driver.manage().window().setSize(new Dimension(1440, 900)); // run in full screen mode (useful for headless)
		}

		else if (browserName.equalsIgnoreCase("firefox"))
		{
			// FF code

			System.getProperty("webdriver.gecko.driver","C:\\1New2\\gecko_driver");
			driver = new FirefoxDriver();


		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			// EDGE code
		}


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}


	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{

		// read JSON to String
		String jsonContent = FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);

		// String to Hashmap via Jackson Bind
		ObjectMapper mapper = new ObjectMapper();

		// data variable will have {map} {map2} {map3} and so
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});

		return data;

	}


	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	}


}