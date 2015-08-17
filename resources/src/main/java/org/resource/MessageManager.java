package org.resource;

import java.util.ResourceBundle;
import static org.resource.Constant.*;

public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_CONFIG);

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
