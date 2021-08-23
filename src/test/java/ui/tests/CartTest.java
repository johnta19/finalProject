package ui.tests;

import DAO.Item;
import base.test.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static steps.BayerSteps.*;
import static ui.tests.LoginTest.correctUser;
import static ui.tests.LoginTest.password;

public class CartTest extends BaseTest {
    public static Item sauceLabsBackpack = Item.builder()
            .name("Sauce Labs Backpack").price("$29.99").quantity("1").build();

    private static Item sauceLabsBoltTShort = Item.builder()
            .name("Sauce Labs Bolt T-Shirt").price("$15.99").quantity("1").build();

    private static Item sauceLabsOnesie = Item.builder()
            .name("Sauce Labs Onesie").price("$7.99").quantity("1").build();

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
                .checkItemAtProductPage(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice())
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice(), sauceLabsBackpack.getQuantity());
    }

    @Description("User should be able to remove item from the cart")
    @Test
    public void removeItemFromCart() {
        chooseItemSauceLabsBackpack()
                .checkItemAtProductPage(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice())
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice(), sauceLabsBackpack.getQuantity())
                .clickRemoveButton()
                .checkRemoveItemFromCart(sauceLabsBackpack.getName());
    }

    @Description("Continue shopping button should redirect user to the All items page")
    @Test
    public void continueShopping() {
        chooseItemSauceLabsBackpack()
                .checkItemAtProductPage(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice())
                .clickAddToCartButton()
                .clickCartButton()
                .checkItemAtCart(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice(), sauceLabsBackpack.getQuantity())
                .clickContinueShoppingButton();
    }

    @Description("User should be able to add multiple items to the cart")
    @Test
    public void addMultipleItemsToCart() {
        addToCartItemSauceLabsBackpack()
                .addToCartItemSauceLabsBoltTShirt()
                .addToCartItemSauceLabsOnesie()
                .clickCartButton()
                .checkItemAtCart(sauceLabsBackpack.getName(), sauceLabsBackpack.getPrice(), sauceLabsBackpack.getQuantity())
                .checkItemAtCart(sauceLabsBoltTShort.getName(), sauceLabsBoltTShort.getPrice(), sauceLabsBoltTShort.getQuantity())
                .checkItemAtCart(sauceLabsOnesie.getName(), sauceLabsOnesie.getPrice(), sauceLabsOnesie.getQuantity());
    }

}
