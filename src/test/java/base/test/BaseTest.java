package base.test;

import browser.provider.BrowserProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import steps.BayerSteps;

import java.util.concurrent.TimeUnit;

public class BaseTest  {
    public static WebDriver driver;
    private WebStorage webStorage;
    private static final String URL = "https://www.saucedemo.com";
    private static final int TIMEOUT = 10;
    private BrowserProvider browserProvider;

    @BeforeSuite
    public void initDriver() {
        browserProvider = new BrowserProvider();
        browserProvider.getBrowser();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        BayerSteps.init();
    }

    @BeforeTest
    public void goToUrl() {
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void clearLocalStorage() {
        webStorage = (WebStorage)driver;
        webStorage.getLocalStorage().clear();
    }


    @AfterSuite
    public void quiteDriver() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
