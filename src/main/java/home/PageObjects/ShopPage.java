package home.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import home.AbstractComponents.AbstractComponents;

public class ShopPage extends AbstractComponents {

    WebDriver driver;

    public ShopPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='iphone X']")
    WebElement iphoneX;

    By productBy = By.xpath("//a[text()='iphone X']");

    public boolean isIphoneXDisplayed() {
        waitForElementToAppear(productBy);
        return iphoneX.isDisplayed();
    }
}
