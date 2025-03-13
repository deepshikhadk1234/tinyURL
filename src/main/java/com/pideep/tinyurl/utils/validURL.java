package com.pideep.tinyurl.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class validURL {
    public static boolean isValidURL(String longURL) {
        if(longURL == null || longURL.isEmpty()) {
            return false;
        }
        // Check for double https:// pattern
        if (longURL.toLowerCase().matches(".*https://.*https://.*")) {
            return false;
        }
        try {
            URI uri = new URI(longURL);
            String host = uri.getHost();
            // Check if host is not null and doesn't contain illegal characters
            return host != null && !host.contains("://") && !host.isEmpty();
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
