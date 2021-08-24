package ui.pages.checkout.pages;

import ui.base.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.Assertion;

public class CheckoutStepOnePage extends BasePage {
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    private Assertion assertion = new Assertion();

    private String currentUrl;
    private String checkoutStepTwoPath = "checkout-step-two";

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipPostalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutStepOnePage fillCheckoutForm(String firstname, String lastname, String postalCode) {
        waitForElementToBeClickable(firstName);
        firstName.sendKeys(firstname);
        waitForElementToBeClickable(lastName);
        lastName.sendKeys(lastname);
        waitForElementToBeClickable(zipPostalCode);
        zipPostalCode.sendKeys(postalCode);
        waitForElementToBeClickable(continueButton);
        continueButton.click();
        currentUrl = driver.getCurrentUrl();
        currentUrl.contains(checkoutStepTwoPath);
        return new CheckoutStepOnePage(driver);
    }

    private By namePriceQuantityXpath(String name, String price, String quantity) {
        return By.xpath("//*[text()=\"" + name +"\"]//ancestor::div[@class='cart_item']//div[@class='inventory_item_price' and contains(., \"" + price + "\")]//ancestor::div[@class='cart_item']//div[@class = 'cart_quantity' and text() = \"" + quantity + "\"]");
    }

    public CheckoutStepTwoPage checkItemAtOrder(String name, String price, String quantity){
        waitForElementToPresence(namePriceQuantityXpath(name, price, quantity));
        return new CheckoutStepTwoPage(driver);
    }

}
