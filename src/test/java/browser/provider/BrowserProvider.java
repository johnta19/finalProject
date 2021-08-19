package browser.provider;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import properties.ConfigProperties;

import static base.test.BaseTest.browserName;

public class BrowserProvider {
  /*      ConfigProperties.getProperties();
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
    }*/
}
