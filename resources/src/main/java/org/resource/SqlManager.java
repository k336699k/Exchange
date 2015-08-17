package org.resource;

import static org.resource.Constant.*;

import java.util.ResourceBundle;
public class SqlManager {
	
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(SQL_CONFIG);
    
    
    private SqlManager () {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


