package com.pideep.tinyurl.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class URLMapping {
    @Setter
    @Getter
    private Long id;
    @NotBlank(message = "URL cannot be blank")
    private final String longURL;
    private final String shortURL;
    private final LocalDateTime createdAt;

    public URLMapping(String longURL, String shortURL) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        this.createdAt = LocalDateTime.now();
    }


    public String getLongURL() {
        return longURL;
    }
    public String getShortURL() {
        return shortURL;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
