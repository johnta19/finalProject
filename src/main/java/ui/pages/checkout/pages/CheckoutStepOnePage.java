package ui.pages.checkout.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.page.BasePage;

public class CheckoutStepOnePage extends BasePage {
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    private String checkoutStepTwoPath = "checkout-step-two";

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipPostalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @Step("Fill checkout form with firstname {0}, lastname {1}, postal code {2}")
    public CheckoutStepOnePage fillCheckoutForm(String firstname, String lastname, String postalCode) {
        waitForElementToBeClickable(firstName);
        firstName.sendKeys(firstname);
        waitForElementToBeClickable(lastName);
        lastName.sendKeys(lastname);
        waitForElementToBeClickable(zipPostalCode);
        zipPostalCode.sendKeys(postalCode);
        waitForElementToBeClickable(continueButton);
        continueButton.click();
        validateUrl(checkoutStepTwoPath);
        return new CheckoutStepOnePage(driver);
    }

    private By namePriceQuantityXpath(String name, String price, String quantity) {
        return By.xpath("//*[text()=\"" + name +"\"]//ancestor::div[@class='cart_item']//div[@class='inventory_item_price' and contains(., \"" + price + "\")]//ancestor::div[@class='cart_item']//div[@class = 'cart_quantity' and text() = \"" + quantity + "\"]");
    }

    @Step("Check item {0}, price {1}, quantity {2} at order page")
    public CheckoutStepTwoPage checkItemAtOrder(String name, String price, String quantity){
        waitForElementToPresence(namePriceQuantityXpath(name, price, quantity));
        return new CheckoutStepTwoPage(driver);
    }

}
