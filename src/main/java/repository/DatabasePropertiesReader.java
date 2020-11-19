package repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class DatabasePropertiesReader {
    public static String getProperties(String propertyKey) {
        String rootPath = Objects.requireNonNull(Thread.currentThread().
                getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "application.properties";
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(appConfigPath)) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error of IO");
        }
        return properties.getProperty(propertyKey);
    }
}
