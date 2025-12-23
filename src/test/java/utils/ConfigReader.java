package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream input =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream("config.properties");
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("config.properties could not be loaded", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}