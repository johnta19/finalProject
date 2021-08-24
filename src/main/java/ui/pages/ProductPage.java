package ui.pages;

import ui.base.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    public MainPage clickAddToCartButton() {
        waitForElementToBeClickable(addToCartButton);
        addToCartButton.click();
        return new MainPage(driver);
    }

}
