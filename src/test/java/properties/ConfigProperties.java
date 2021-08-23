package properties;

import base.test.BaseTest;
import ui.tests.LoginTest;
import ui.tests.OrderTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
    static Properties prop = new Properties();
    static String projectPath = System.getProperty("user.dir");

    public static void getProperties() {
        try {
            InputStream input = new FileInputStream(projectPath + "/src/main/resources/config.properties");
            prop.load(input);
            String browser = prop.getProperty("browser");
            String correctUser = prop.getProperty("correctUser");
            String lockedUser = prop.getProperty("lockedUser");
            String incorrectUser = prop.getProperty("incorrectUser");
            String password = prop.getProperty("password");
            String firstname = prop.getProperty("firstname");
            String lastname = prop.getProperty("lastname");
            String postalCode = prop.getProperty("postalCode");
            BaseTest.browserName = browser;
            LoginTest.correctUser = correctUser;
            LoginTest.lockedUser = lockedUser;
            LoginTest.incorrectUser = incorrectUser;
            LoginTest.password = password;
            OrderTest.firstname = firstname;
            OrderTest.lastname = lastname;
            OrderTest.postalCode = postalCode;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
