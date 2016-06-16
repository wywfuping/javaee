package com.fuping.util;

import java.io.IOException;
import java.util.Properties;

public class Config {
    public static Properties properties = new Properties();
    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("连接config.properties异常",e);
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
    public static String get(String key,String def){
        return properties.getProperty(key,def);
    }
}
