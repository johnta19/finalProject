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

    public static String getConfigProperty(String key) {
        try {
            input = new FileInputStream(projectPath + "/src/main/resources/config.properties");
            prop.load(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
