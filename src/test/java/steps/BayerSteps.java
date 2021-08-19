package steps;

import base.test.BaseTest;
import org.testng.asserts.Assertion;
import org.testng.reporters.jq.Main;
import pages.LoginPage;
import pages.MainPage;

public class BayerSteps extends BaseTest {
    private static LoginPage loginPage = new LoginPage(getDriver());

    public static MainPage correctLogIn(String user, String pass) {
        return loginPage.login(user, pass).logOut();
    }

    public static MainPage incorrectLogIn(String user, String pass) {
        return loginPage.login(user, pass);
    }

    public static MainPage lockedLogIn(String user, String pass) {
        return loginPage.login(user, pass);
    }
}
