package com.pideep.tinyurl.service;

import com.pideep.tinyurl.model.URLMapping;
import com.pideep.tinyurl.repository.InMemoryURLRepository;
import com.pideep.tinyurl.utils.validURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class URLService {
    @Autowired
    private InMemoryURLRepository inMemoryURLRepository;
    private final String baseURL = "http://localhost:8080/"; //the base URL for the tinyURL service


    public String generateShortURL(String longURL) throws IllegalArgumentException {
        if(!validURL.isValidURL(longURL)){
            throw new IllegalArgumentException("Invalid URL");
        }

        // Check if the incoming URL is already a short URL in our system
        if(longURL.startsWith(baseURL)) {
            String shortCode = longURL.substring(baseURL.length());
            if(inMemoryURLRepository.findByShortCode(shortCode) != null) {
                throw new IllegalArgumentException("URL is already a shortened URL");
            }
        }

        if(inMemoryURLRepository.longURLExits(longURL)){
            URLMapping urlMapping = inMemoryURLRepository.findByLongURL(longURL);
            return baseURL+urlMapping.getShortURL();
        }

        URLMapping urlMapping = inMemoryURLRepository.saveURL(longURL);
        return baseURL + urlMapping.getShortURL();
    }

    public String retrieveLongURL(String shortURL) throws IllegalArgumentException {
        URLMapping urlMapping = inMemoryURLRepository.findByShortCode(shortURL);

        if(urlMapping == null){
            throw new IllegalArgumentException("Short URL dosen't exist, create one");
        }
        return urlMapping.getLongURL();
    }

}
