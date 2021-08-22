package pages;

import base.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.Assertion;
import pages.checkout.pages.CheckoutStepOnePage;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private Assertion assertion = new Assertion();

    private String currentUrl;
    private String checkoutStepOnePath = "checkout-step-one";
    private String mainPagePath = "inventory";

    private By itemNameAtCart = By.id("cart_contents_container");

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;


    private By namePriceQuantityXpath(String name, String price, String quantity) {
        return By.xpath("//*[text()=\"" + name +"\"]//ancestor::div[@class='cart_item']//div[@class='inventory_item_price' and contains(., \"" + price + "\")]//ancestor::div[@class='cart_item']//div[@class = 'cart_quantity' and text() = \"" + quantity + "\"]");
    }

    public CartPage checkItemAtCart(String name, String price, String quantity){
        waitForElementToPresence(namePriceQuantityXpath(name, price, quantity));
        return new CartPage(driver);
    }

    public CartPage checkRemoveItemFromCart(String name) {
        waitForTextHasNotContent(itemNameAtCart, name);
        return new CartPage(driver);
    }

    public CartPage clickRemoveButton() {
        waitForElementToBeClickable(removeButton);
        removeButton.click();
        return new CartPage(driver);
    }

    public CheckoutStepOnePage clickCheckoutButton() {
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(checkoutStepOnePath);
        waitForElementToBeClickable(checkoutButton);
        checkoutButton.click();
        return new CheckoutStepOnePage(driver);
    }

    public void clickContinueShoppingButton() {
        waitForElementToBeClickable(continueShoppingButton);
        continueShoppingButton.click();
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(mainPagePath);
    }

    public void checkoutButtonNotClickable() {
        if(waitForElementToBeClickable(checkoutButton)) {
            assertion.fail("checkoutButton is clickable");
        }

    }
}

