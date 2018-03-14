package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Igor Odokienko
 */
public class PropertyConfig {

    private static String pathToPropertyFile = "src/main/resources/test.properties";
    private static Properties properties = null;

    private PropertyConfig() {
    }

    private static void loadProperties() {

        File file = new File(pathToPropertyFile);

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties = new Properties();

        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(propertyName);
    }

}
