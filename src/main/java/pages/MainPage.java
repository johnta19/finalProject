package pages;

import base.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement leftSideMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logOutButton;

    public MainPage logOut() {
        waitForElementToBeClickable(leftSideMenu);
        leftSideMenu.click();
        waitForElementToBeClickable(logOutButton);
        logOutButton.click();
        return new MainPage(driver);
    }
}
