package ui.pages.checkout.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.base.page.BasePage;

public class OrderInformationPage extends BasePage {

    public OrderInformationPage(WebDriver driver) {
        super(driver);
    }

    private By itemName = By.className("inventory_item_name");
    private By itemPrice = By.className("inventory_item_price");
    private By checkoutMassage = By.className("header_secondary_container");

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    @Step("Click finish button")
    public OrderInformationPage clickFinishButton() {
        validateText(itemName, "Sauce Labs Backpack");
        validateText(itemPrice, "$29.99");
        waitForElementToBeClickable(finishButton);
        finishButton.click();
        validateUrl("checkout-complete");
        return new OrderInformationPage(driver);
    }

    @Step("Check order result, should be COMPLETE")
    public OrderInformationPage checkOrderResult() {
        waitForTextHasContent(checkoutMassage, "COMPLETE");
        return new OrderInformationPage(driver);
    }

    @Step("Click back to products button")
    public void clickBackHomeButton() {
        waitForElementToBeClickable(backHomeButton);
        backHomeButton.click();
    }

}
