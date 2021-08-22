package ui.tests;

import base.test.BaseTest;
import io.qameta.allure.Description;
import listeners.ListenersTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static steps.BayerSteps.*;

@Listeners({ListenersTest.class})
public class OrderTest extends BaseTest {

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
                .checkItemAtProductPage(nameSauceLabsBackpack, priceSauceLabsBackpack)
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(nameSauceLabsBackpack, priceSauceLabsBackpack, quantity)
                .clickCheckoutButton()
                .fillCheckoutForm(firstname, lastname, postalCode)
                .checkItemAtOrder(nameSauceLabsBackpack, priceSauceLabsBackpack, quantity)
                .clickFinishButton()
                .checkOrderResult()
                .clickBackToProductsButton();
    }

    @Description("User should not be able to create order when cart is empty")
    @Test
    public void createOrderWithEmptyCart() {
        clickCartButton()
                .checkoutButtonNotClickable();

    }
}
