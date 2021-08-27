package ui.base.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

public class BasePage {
    private static final int TIMEOUT = 15;
    private static final int POLLING = 100;

    protected WebDriver driver;
    private WebDriverWait wait;
    private Assertion assertion = new Assertion();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return true;
    }

    protected void waitForElementNotToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
    }

    protected void waitForElementToPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToDisappear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }

    protected void waitForTextHasContent(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    protected void waitForTextHasNotContent(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(locator, text)));
    }

    protected boolean validateUrl(String url) {
        return driver.getCurrentUrl().contains(url);
    }

    protected void validateText(By locator, String text) {
        assertion.assertEquals(driver.findElement(locator).getText(), text);
    }
}
