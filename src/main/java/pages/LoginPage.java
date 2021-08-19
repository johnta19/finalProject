package pages;

import base.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.Assertion;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private Assertion assertion = new Assertion();
    private String incorrectLoginMassage = "Epic sadface: Username and password do not match any user in this service";
    private String lockedLoginMassage = "Epic sadface: Sorry, this user has been locked out.";
    private String massage;
    private Actions actions = new Actions(driver);

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMassage;


    @Step("Log in step with username {0}, password {1}")
    public MainPage login(String user, String pass) {
        waitForElementToBeClickable(username);
        actions.doubleClick(username).build().perform();
        username.sendKeys(user);
        waitForElementToBeClickable(password);
        actions.doubleClick(password).build().perform();
        password.sendKeys(pass);
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        return new MainPage(driver);
    }

    public LoginPage assertionForIncorrectLogin() {
        waitForElementToAppear((By)errorMassage);
        massage = errorMassage.getText();
        assertion.assertEquals(massage, incorrectLoginMassage);
        return new LoginPage(driver);
    }

    public LoginPage assertionForLockedLogin() {
        waitForElementToAppear((By)errorMassage);
        massage = errorMassage.getText();
        assertion.assertEquals(massage, incorrectLoginMassage);
        return new LoginPage(driver);
    }
}
