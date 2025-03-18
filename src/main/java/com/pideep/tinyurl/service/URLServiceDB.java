package com.pideep.tinyurl.service;

import com.pideep.tinyurl.model.URLMappingDB;
import com.pideep.tinyurl.repository.URLRepository;
import com.pideep.tinyurl.utils.Base62Encoder;
import com.pideep.tinyurl.utils.validURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLServiceDB implements URLServiceInterface {

    private final URLRepository urlRepository;
    
    private final String baseURL = "http://localhost:8080/api/v1/";
    private static long counter = 0;

    public URLServiceDB(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    @Override
    public String generateShortURL(String longURL) throws IllegalArgumentException {
        if (!validURL.isValidURL(longURL)) {
            throw new IllegalArgumentException("Invalid URL");
        }

        // Check if the incoming URL is already a short URL in our system
        if (longURL.startsWith(baseURL)) {
            String shortCode = longURL.substring(baseURL.length());
            if (urlRepository.findByShortURL(shortCode).isPresent()) {
                throw new IllegalArgumentException("URL is already shortened");
            }
        }

        // Check if long URL already exists
        return urlRepository.findByLongURL(longURL)
                .map(mapping -> baseURL + mapping.getShortURL())
                .orElseGet(() -> {
                    String shortCode = Base62Encoder.encode(++counter);
                    URLMappingDB urlMapping = new URLMappingDB(longURL, shortCode);
                    urlRepository.save(urlMapping);
                    return baseURL + shortCode;
                });
    }

    @Override
    public String retrieveLongURL(String shortURL) throws IllegalArgumentException {
        return urlRepository.findByShortURL(shortURL)
                .map(URLMappingDB::getLongURL)
                .orElseThrow(() -> new IllegalArgumentException("Short URL doesn't exist"));
    }
}

