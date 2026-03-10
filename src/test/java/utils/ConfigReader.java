package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read configuration from properties file
 */
public class ConfigReader {
    
    private static Properties properties;
    
    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
    
    public static int getConnectionTimeout() {
        return Integer.parseInt(properties.getProperty("connectionTimeout"));
    }
    
    public static int getSocketTimeout() {
        return Integer.parseInt(properties.getProperty("socketTimeout"));
    }
}
