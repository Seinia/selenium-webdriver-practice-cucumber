package util;

import java.util.ResourceBundle;

public class TestDataReader {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment", "qa"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
