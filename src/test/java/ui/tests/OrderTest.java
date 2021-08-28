package ui.tests;

import base.test.BaseTest;
import io.qameta.allure.Description;
import listeners.ListenersTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static steps.BuyerSteps.*;
import static ui.tests.CartTest.sauceLabsBackpack;
import static ui.tests.LoginTest.correctUser;
import static ui.tests.LoginTest.password;

@Listeners(ListenersTest.class)
public class OrderTest extends BaseTest {
    public static String firstname;
    public static String lastname;
    public static String postalCode;

    @BeforeMethod
    public void logIn() {
        correctLogIn(correctUser, password);
    }

    @AfterMethod
    public void logOut() {
        correctLogOut();
    }

    @Description("Order should be created when user finished checkout flow")
    @Test
    public void endToEndBuy() {
        chooseItemSauceLabsBackpack()
                .checkItemAtProductPage(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice())
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice(), sauceLabsBackpack.getQuantity())
                .clickCheckoutButton()
                .fillCheckoutForm(firstname, lastname, postalCode)
                .checkItemAtOrder(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice(), sauceLabsBackpack.getQuantity())
                .clickFinishButton()
                .checkOrderResult()
                .clickBackHomeButton();
    }

    @Description("User should not be able to create order when cart is empty")
    @Test
    public void createOrderWithEmptyCart() {
        clickCartButton()
                .checkoutButtonNotClickable();

    }
}
