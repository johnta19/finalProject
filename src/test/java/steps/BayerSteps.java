package steps;

import base.test.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.MainPage;

import static base.test.BaseTest.getDriver;

public class BayerSteps {
    private static LoginPage loginPage;
    private static MainPage mainPage;

    public static LoginPage correctLogIn(String user, String pass) {
        return loginPage.login(user, pass);
    }

    public static void init(){
        loginPage = new LoginPage(getDriver());
        mainPage = new MainPage(getDriver());
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
