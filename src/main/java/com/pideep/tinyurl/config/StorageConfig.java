package com.pideep.tinyurl.config;

import com.pideep.tinyurl.utils.StorageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Value("${storage.type}")
    private String storageType;

    public StorageType getStorageType() {
        return StorageType.valueOf(storageType.toUpperCase());
    }
} 