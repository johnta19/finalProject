package properties;

import api.tests.CrudRequestTest;
import browser.provider.BrowserProvider;
import ui.tests.LoginTest;
import ui.tests.OrderTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
    private static Properties prop = new Properties();
    private static String projectPath = System.getProperty("user.dir");
    private static InputStream input;

    public static void getBrowserProperties() {
        try {
            input = new FileInputStream(projectPath + "/src/main/resources/config.properties");
            prop.load(input);
            BrowserProvider.browserName = prop.getProperty("browser");

            LoginTest.correctUser = prop.getProperty("correctUser");
            LoginTest.lockedUser = prop.getProperty("lockedUser");
            LoginTest.incorrectUser = prop.getProperty("incorrectUser");
            LoginTest.password = prop.getProperty("password");

            OrderTest.firstname = prop.getProperty("firstname");
            OrderTest.lastname = prop.getProperty("lastname");
            OrderTest.postalCode = prop.getProperty("postalCode");

            CrudRequestTest.loginApi = prop.getProperty("loginApi");
            CrudRequestTest.passwordApi = prop.getProperty("passwordApi");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static void getUiCredentialsProperties() {
        try {
            input = new FileInputStream(projectPath + "/src/main/resources/config.properties");
            prop.load(input);

            LoginTest.correctUser = prop.getProperty("correctUser");
            LoginTest.lockedUser = prop.getProperty("lockedUser");
            LoginTest.incorrectUser = prop.getProperty("incorrectUser");
            LoginTest.password = prop.getProperty("password");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static void getBuyerProperties() {
        try {
            input = new FileInputStream(projectPath + "/src/main/resources/config.properties");
            prop.load(input);

            OrderTest.firstname = prop.getProperty("firstname");
            OrderTest.lastname = prop.getProperty("lastname");
            OrderTest.postalCode = prop.getProperty("postalCode");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static void getApiCredentialsProperties() {
        try {
            input = new FileInputStream(projectPath + "/src/main/resources/config.properties");
            prop.load(input);

            CrudRequestTest.loginApi = prop.getProperty("loginApi");
            CrudRequestTest.passwordApi = prop.getProperty("passwordApi");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
