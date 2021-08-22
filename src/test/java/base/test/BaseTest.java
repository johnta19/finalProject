package base.test;

import browser.provider.BrowserProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import static properties.ConfigProperties.getProperties;

public class BaseTest  {
    private static WebDriver driver;
    private WebStorage webStorage;
    public static String browserName;
    public static String correctUser;
    public static String lockedUser;
    public static String incorrectUser;
    public static String password;
    public static String firstname;
    public static String lastname;
    public static String postalCode;
    public String nameSauceLabsBackpack = "Sauce Labs Backpack";
    public String priceSauceLabsBackpack = "$29.99";
    public String quantity = "1";
    private BrowserProvider browserProvider;
    private static final String URL = "https://www.saucedemo.com";
    private static final int TIMEOUT = 10;

    @BeforeSuite
    public void beforeSuite() {
        getProperties();
        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("opera")) {
            System.setProperty("webdriver.opera.driver", "operadriver.exe");
            driver = new OperaDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
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

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
