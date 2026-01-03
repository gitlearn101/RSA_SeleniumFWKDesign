package home.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import home.PageObjects.LoginPagePractise;
import home.PageObjects.ShopPage;
import home.TestComponent.BaseTest;

public class IphoneXTest extends BaseTest {

    @Test
    public void verifyIphoneXPresence() {
        LoginPagePractise loginPage = new LoginPagePractise(driver);
        loginPage.goTo();
        ShopPage shopPage = loginPage.login("rahulshettyacademy", "learning");
        Assert.assertTrue(shopPage.isIphoneXDisplayed());
    }
}
