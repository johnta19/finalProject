package base.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import properties.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    public static String browserName;
    public static String correctUser;
    public static String lockedUser;
    public static String incorrectUser;
    public static String password;
    private String url = "https://www.saucedemo.com";

    @BeforeSuite
    public void beforeSuite() {
        ConfigProperties.getProperties();
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @BeforeTest
    public void goToUrl() {
        driver.get(url);
        driver.manage().window().maximize();
    }

//    @BeforeClass
//    public void logIn() {
//        mainPage.clickLoginButton();
//        loginPage.logIn(login, password);
//    }
//    @AfterClass
//    public void logOut() {
//        homePage.clickLogoutButton();
//    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    public static WebDriver getDriver() {

        return driver;
    }
}
