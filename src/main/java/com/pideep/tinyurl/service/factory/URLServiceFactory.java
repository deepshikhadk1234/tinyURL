package com.pideep.tinyurl.service.factory;

import com.pideep.tinyurl.repository.InMemoryURLRepository;
import com.pideep.tinyurl.repository.URLRepository;
import com.pideep.tinyurl.service.URLService;
import com.pideep.tinyurl.service.URLServiceDB;
import com.pideep.tinyurl.service.URLServiceInterface;
import com.pideep.tinyurl.utils.StorageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class URLServiceFactory {
    
    private URLServiceDB urlServiceDB;

    private URLService urlService;


    public URLServiceInterface getURLServiceType(StorageType storageType) {
        return switch (storageType) {
            case IN_MEMORY -> urlService;
            case POSTGRES -> urlServiceDB;
            default -> throw new IllegalArgumentException("Unsupported storage type: " + storageType);
        };
    }
}
