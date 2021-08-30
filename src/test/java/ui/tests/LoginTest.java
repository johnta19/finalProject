package ui.tests;

import base.test.BaseTest;
import io.qameta.allure.Description;
import listeners.ListenersTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static properties.ConfigProperties.getConfigProperty;
import static steps.BuyerSteps.*;

@Listeners(ListenersTest.class)
public class LoginTest extends BaseTest {
    public static String correctUser;
    public static String lockedUser;
    public static String incorrectUser;
    public static String password;

    @BeforeTest
    public void getCredentials() {
        correctUser = getConfigProperty("correctUser");
        lockedUser = getConfigProperty("lockedUser");
        incorrectUser = getConfigProperty("incorrectUser");
        password = getConfigProperty("password");
    }

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
