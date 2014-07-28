package com.maximgalushka.http;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    private static final Logger log = Logger.getLogger(PropertiesHelper.class);

    private static final PropertiesHelper helper = new PropertiesHelper();
    private Properties properties;

    private PropertiesHelper()  {
        try {
            properties = new Properties();
            properties.load(PropertiesHelper.class.getResourceAsStream("/settings.properties"));
        } catch (IOException e) {
            log.fatal("Properties loading failure");
        }
    }

    public static PropertiesHelper getHelper(){
        return helper;
    }

    public String getProperty(String key){
        if(properties == null || properties.isEmpty())
            throw new RuntimeException("Properties were not initialized");

        return properties.getProperty(key, null);
    }
}
