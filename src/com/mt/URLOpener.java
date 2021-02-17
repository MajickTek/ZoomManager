package com.mt;

import java.awt.*;
import java.net.URI;

public class URLOpener {
    public static void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            Debug.callCrashDialog("Error", "Could not join meeting.\nReason: " + e.getMessage(), Debug.ERR);
        }
    }

}
