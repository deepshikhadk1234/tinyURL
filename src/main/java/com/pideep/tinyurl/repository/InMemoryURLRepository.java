package com.pideep.tinyurl.repository;

import com.pideep.tinyurl.model.URLMapping;
import com.pideep.tinyurl.utils.Base62Encoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryURLRepository {
    //stores short code to URL mapping
    private final Map<String, URLMapping> shortCodeToURLMap = new ConcurrentHashMap<>();

    //maps long URL to short URL to avoid duplications
    private final Map<String, String> longURLToShortCodeMap = new ConcurrentHashMap<>();

    private long idGenerator = 0;

    public URLMapping saveURL(String longURL) {
        if(longURLToShortCodeMap.containsKey(longURL)) {
            return shortCodeToURLMap.get(longURLToShortCodeMap.get(longURL));
        }

        String shortCode = generateShortCode();

        URLMapping urlMapping = new URLMapping(longURL, shortCode);
        urlMapping.setId(++idGenerator);

        shortCodeToURLMap.put(shortCode, urlMapping);
        longURLToShortCodeMap.put(longURL, shortCode);

        return urlMapping;

    }

    public String generateShortCode() {
        return Base62Encoder.encode(idGenerator+1);
    }

    public URLMapping findByShortCode(String shortCode) {
        URLMapping urlMapping = shortCodeToURLMap.get(shortCode);
        if(urlMapping == null) {
//            System.out.println("No URL Mapping found for shortCode: " + shortCode);
            return null;
        }
        return shortCodeToURLMap.get(shortCode);
    }

    public boolean longURLExits(String longCode) {
        return longURLToShortCodeMap.containsKey(longCode);
    }


    public URLMapping findByLongURL(String longURL) {
        String shortURL =  longURLToShortCodeMap.get(longURL);
        return shortCodeToURLMap.get(shortURL);
    }

}
