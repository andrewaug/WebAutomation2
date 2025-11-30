package com.amazon.utils;

import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static Properties initProperties() {
        if (prop == null) {
            prop = new Properties();
            try {
                // Load properties file from the classpath
                prop.load(ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

}
