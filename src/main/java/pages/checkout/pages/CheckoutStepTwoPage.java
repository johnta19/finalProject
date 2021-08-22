package pages.checkout.pages;

import base.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.Assertion;

public class CheckoutStepTwoPage extends BasePage {

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    private Assertion assertion = new Assertion();

    private String currentUrl;
    private String checkoutCompletePath = "checkout-complete";
    private String expectedItemName = "Sauce Labs Backpack";
    private String expectedItemPrice = "$29.99";
    private String expectedMassage = "COMPLETE";

    private By itemName = By.className("inventory_item_name");
    private By itemPrice = By.className("inventory_item_price");
    private By checkoutMassage = By.className("header_secondary_container");

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    public CheckoutStepTwoPage clickFinishButton() {
        assertion.assertEquals(driver.findElement(itemName).getText(), expectedItemName);
        assertion.assertEquals(driver.findElement(itemPrice).getText(), expectedItemPrice);
        waitForElementToBeClickable(finishButton);
        finishButton.click();
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(checkoutCompletePath);
        return new CheckoutStepTwoPage(driver);
    }

    public CheckoutStepTwoPage checkOrderResult() {
        waitForTextHasContent(checkoutMassage, expectedMassage);
        return new CheckoutStepTwoPage(driver);
    }

    public void clickBackToProductsButton() {
        waitForElementToBeClickable(backToProductsButton);
        backToProductsButton.click();
    }

}
