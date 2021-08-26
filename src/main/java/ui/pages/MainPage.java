package ui.pages;

import ui.base.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.Assertion;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private String currentUrl;
    private String productPagePath = "inventory-item";
    private String cartPath = "cart";

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(xpath = "//div[normalize-space()='Sauce Labs Backpack']")
    private WebElement sauceLabsBackPack;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartSauceLabsBackpackButton;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addToCartSauceLabsBoltTShirtButton;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement addToCartaSauceLabsOnesieButton;

    @Step("Choose Sauce Labs Backpack ")
    public MainPage chooseItemSauceLabsBackpack() {
        waitForElementToBeClickable(sauceLabsBackPack);
        sauceLabsBackPack.click();
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(productPagePath);
        return new MainPage(driver);
    }

    private By namePriceXpath(String name, String price) {
        return By.xpath("//*[text()=\"" + name + "\"]//ancestor::div[@class='inventory_details_container']//div[@class='inventory_details_price' and contains(., \"" + price + "\")]");
    }

    @Step("Check item {0}, price {1} at product page")
    public ProductPage checkItemAtProductPage(String name, String price) {
        waitForElementToPresence(namePriceXpath(name, price));
        return new ProductPage(driver);
    }

    @Step("Add to cart Sauce Labs Backpack ")
    public MainPage addToCartSauceLabsBackpack() {
        waitForElementToBeClickable(addToCartSauceLabsBackpackButton);
        addToCartSauceLabsBackpackButton.click();
        return new MainPage(driver);
    }

    @Step("Add to cart Sauce Labs Bolt T Shirt")
    public MainPage addToCartItemSauceLabsBoltTShirt() {
        waitForElementToBeClickable(addToCartSauceLabsBoltTShirtButton);
        addToCartSauceLabsBoltTShirtButton.click();
        return new MainPage(driver);
    }

    @Step("Add to cart Sauce Labs Onesie ")
    public MainPage addToCartItemSauceLabsOnesie() {
        waitForElementToBeClickable(addToCartaSauceLabsOnesieButton);
        addToCartaSauceLabsOnesieButton.click();
        return new MainPage(driver);
    }

    @Step("Click cart button")
    public CartPage clickCartButton() {
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(cartPath);
        waitForElementToBeClickable(cartButton);
        cartButton.click();
        return new CartPage(driver);
    }

}
