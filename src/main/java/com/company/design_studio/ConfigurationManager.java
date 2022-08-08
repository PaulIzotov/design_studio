package com.company.design_studio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigurationManager {
    INSTANCE;

    public static final String CONFIG_FILE = "/application.properties";
    private final Properties properties;

    ConfigurationManager() {
        try (InputStream in = getClass().getResourceAsStream(CONFIG_FILE)) {
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

