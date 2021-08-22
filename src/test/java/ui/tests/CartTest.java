package ui.tests;

import base.test.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static steps.BayerSteps.*;

public class CartTest extends BaseTest {

    private String nameSauceLabsBoltTShirt = "Sauce Labs Bolt T-Shirt";
    private String priceSauceLabsBoltTShirt = "$15.99";
    private String nameSauceLabsOnesie = "Sauce Labs Onesie";
    private String priceSauceLabsOnesie = "$7.99";

    @BeforeMethod
    public void logIn() {
        correctLogIn(correctUser, password);
    }

    @AfterMethod
    public void logOut() {
        correctLogOut();
    }

    @Description("User should be able to add item to the cart")
    @Test
    public void addItemToCart() {
        chooseItemSauceLabsBackpack()
                .checkItemAtProductPage(nameSauceLabsBackpack, priceSauceLabsBackpack)
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(nameSauceLabsBackpack, priceSauceLabsBackpack, quantity);
    }

    @Description("User should be able to remove item from the cart")
    @Test
    public void removeItemFromCart() {
        chooseItemSauceLabsBackpack()
                .checkItemAtProductPage(nameSauceLabsBackpack, priceSauceLabsBackpack)
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(nameSauceLabsBackpack, priceSauceLabsBackpack, quantity)
                .clickRemoveButton()
                .checkRemoveItemFromCart(nameSauceLabsBackpack);
    }

    @Description("Continue shopping button should redirect user to the All items page")
    @Test
    public void continueShopping() {
        chooseItemSauceLabsBackpack()
                .checkItemAtProductPage(nameSauceLabsBackpack, priceSauceLabsBackpack)
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(nameSauceLabsBackpack, priceSauceLabsBackpack, quantity)
                .clickContinueShoppingButton();
    }

    @Description("User should be able to add multiple items to the cart")
    @Test
    public void addMultipleItemsToCart() {
        addToCartItemSauceLabsBackpack()
                .addToCartItemSauceLabsBoltTShirt()
                .addToCartItemSauceLabsOnesie()
                .clickCartButton()
                .checkItemAtCart(nameSauceLabsBackpack, priceSauceLabsBackpack, quantity)
                .checkItemAtCart(nameSauceLabsBoltTShirt, priceSauceLabsBoltTShirt, quantity)
                .checkItemAtCart(nameSauceLabsOnesie, priceSauceLabsOnesie, quantity);

    }

}
