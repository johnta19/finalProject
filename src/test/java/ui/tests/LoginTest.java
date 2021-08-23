package ui.tests;

import base.test.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static steps.BayerSteps.*;

public class LoginTest extends BaseTest {
    public static String correctUser;
    public static String lockedUser;
    public static String incorrectUser;
    public static String password;

    @Description("Standard user should be able to login")
    @Test
    public void correctLogin() {
        correctLogIn(correctUser, password).logOut();
    }

    @Description("User should not be able to login with incorrect credentials")
    @Test
    public void incorrectLogin() {
        incorrectLogIn(incorrectUser, password).assertionForIncorrectLogin();
    }

    @Description("Locked user should not be able to login")
    @Test
    public void lockedLogin() {
        lockedLogIn(lockedUser, password).assertionForLockedLogin();
    }
}
