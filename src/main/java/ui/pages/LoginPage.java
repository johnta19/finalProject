package ui.pages;

import ui.base.page.BasePage;
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
    private Actions actions = new Actions(driver);

    private String currentUrl;
    private String mainPagePath = "inventory";
    private String incorrectLoginMassage = "Epic sadface: Username and password do not match any user in this service";
    private String lockedLoginMassage = "Epic sadface: Sorry, this user has been locked out.";

    private By errorLoginMassage = By.className("error-message-container");

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMassage;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement leftSideMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logOutButton;


    @Step("Log in  with username {0}, password {1}")
    public LoginPage login(String user, String pass) {
        waitForElementToBeClickable(username);
        actions.doubleClick(username).build().perform();
        username.sendKeys(user);
        waitForElementToBeClickable(password);
        actions.doubleClick(password).build().perform();
        password.sendKeys(pass);
        waitForElementToBeClickable(loginButton);
        loginButton.click();
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(mainPagePath);
        return new LoginPage(driver);
    }

    @Step("Log out")
    public LoginPage logOut() {
        waitForElementToBeClickable(leftSideMenu);
        leftSideMenu.click();
        waitForElementToBeClickable(logOutButton);
        logOutButton.click();
        return new LoginPage(driver);
    }

    @Step("Check error massage for incorrect log in")
    public void assertionForIncorrectLogin() {
        waitForElementToAppear(errorLoginMassage);
        assertion.assertEquals(driver.findElement(errorLoginMassage).getText(), incorrectLoginMassage);
    }

    @Step("Check error massage for locked log in")
    public void assertionForLockedLogin() {
        waitForElementToAppear(errorLoginMassage);
        assertion.assertEquals(driver.findElement(errorLoginMassage).getText(), lockedLoginMassage);
    }
}
