package org.resource;

import static org.resource.Constant.PAGE_CONFIG;

import java.util.ResourceBundle;

public class ConfigurationManager {
private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PAGE_CONFIG);
    
    
    private ConfigurationManager () {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
