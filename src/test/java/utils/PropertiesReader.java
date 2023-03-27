package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Properties properties = new Properties();

    private static void loadProperties() {
        properties.putAll(loadPropertiesFromFile("application.properties"));
    }

    private static Properties loadPropertiesFromFile(String propertiesFile) {
        try (InputStream inputStream =
                     Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        if (properties.isEmpty()) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

}
