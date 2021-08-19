package ui.tests;

import base.test.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import steps.BayerSteps;

public class LoginTest extends BaseTest {

    @Description("Standard user should be able to login")
    @Test
    public void correctLogin() {
        BayerSteps.correctLogIn(correctUser, password);
    }

    @Description("User should not be able to login with incorrect credentials")
    @Test
    public void incorrectLogin() {
        BayerSteps.incorrectLogIn(incorrectUser, password);

    }

    @Description("Locked user should not be able to login")
    @Test
    public void lockedLogin() {
        BayerSteps.lockedLogIn(lockedUser, password);
    }
}
