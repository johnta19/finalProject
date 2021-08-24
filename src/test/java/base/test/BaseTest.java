package base.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.*;
import steps.BayerSteps;

import java.util.concurrent.TimeUnit;

import static properties.ConfigProperties.getProperties;

public class BaseTest  {
    private static WebDriver driver;
    private WebStorage webStorage;
    public static String browserName;
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
    public void afterSuite() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
