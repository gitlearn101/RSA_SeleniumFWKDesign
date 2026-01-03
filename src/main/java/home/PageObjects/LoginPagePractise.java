package home.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import home.AbstractComponents.AbstractComponents;

public class LoginPagePractise extends AbstractComponents {

    WebDriver driver;

    public LoginPagePractise(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "input[type='checkbox']")
    WebElement checkbox;

    @FindBy(id = "signInBtn")
    WebElement signInBtn;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
    }

    public ShopPage login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        checkbox.click();
        signInBtn.click();
        return new ShopPage(driver);
    }
}
