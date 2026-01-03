package home.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import home.AbstractComponents.AbstractComponents;

public class RegistrationPage extends AbstractComponents {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userMobile")
    WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationDropdown;

    @FindBy(css = "input[type='radio'][value='Male']")
    WebElement maleGender;

    @FindBy(css = "input[type='radio'][value='Female']")
    WebElement femaleGender;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(css = "input[formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerButton;

    public void performRegistration(String fName, String lName, String email, String phone, String occupation, String gender, String password, String confirmPwd) {
        // Enter First Name
        firstName.sendKeys(fName);

        // Enter Last Name
        lastName.sendKeys(lName);

        // Enter Email
        userEmail.sendKeys(email);

        // Enter Phone Number
        userMobile.sendKeys(phone);

        // Select Occupation
        Select selectOccupation = new Select(occupationDropdown);
        selectOccupation.selectByVisibleText(occupation);

        // Select Gender
        if (gender.equalsIgnoreCase("Male")) {
            maleGender.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            femaleGender.click();
        }

        // Enter Password
        userPassword.sendKeys(password);

        // Enter Confirm Password
        confirmPassword.sendKeys(confirmPwd);

        // Check the "I am 18 years or Older" checkbox
        ageCheckbox.click();

        // Click the Register button
        registerButton.click();
    }
}
