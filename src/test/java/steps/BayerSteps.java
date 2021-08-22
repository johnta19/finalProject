package steps;

import base.test.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProductPage;

public class BayerSteps extends BaseTest {
    private static LoginPage loginPage = new LoginPage(getDriver());
    private static MainPage mainPage = new MainPage(getDriver());
    private static CartPage cartPage = new CartPage(getDriver());

    public static LoginPage correctLogIn(String user, String pass) {
        return loginPage.login(user, pass);
    }

    public static LoginPage correctLogOut() {
        return loginPage.logOut();
    }

    public static LoginPage incorrectLogIn(String user, String pass) {
        return loginPage.login(user, pass);
    }

    public static LoginPage lockedLogIn(String user, String pass) {
        return loginPage.login(user, pass);
    }

    public static MainPage chooseItemSauceLabsBackpack() {
        return mainPage.chooseItemSauceLabsBackpack();
    }

    public static MainPage addToCartItemSauceLabsBackpack() {
        return mainPage.addToCartSauceLabsBackpack();
    }

    public static CartPage clickCartButton() {
        return mainPage.clickCartButton();
    }


}
