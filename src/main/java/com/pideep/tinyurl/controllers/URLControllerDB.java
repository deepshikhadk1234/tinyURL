package com.pideep.tinyurl.controllers;

import com.pideep.tinyurl.service.factory.URLServiceFactory;
import com.pideep.tinyurl.service.URLServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class URLControllerDB {

    @Autowired
    private URLServiceFactory urlServiceFactory;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> generateShortURL(
            @Valid @RequestBody URLRequest request) {
        try {
            URLServiceInterface urlService = urlServiceFactory.getURLService();
            String shortURL = urlService.generateShortURL(request.longURL());
            return ResponseEntity.ok(new UrlResponse(shortURL));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UrlResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<Void> redirectToLongURL(@PathVariable String shortURL) {
        try {
            URLServiceInterface urlService = urlServiceFactory.getURLService();
            String longURL = urlService.retrieveLongURL(shortURL);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(longURL))
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Record for URL request
    public record URLRequest(
            @jakarta.validation.constraints.NotBlank(message = "URL cannot be blank")
            String longURL
    ) {}

    // Record for URL response
    public record UrlResponse(
            String shortUrl,
            String errorMessage
    ) {
        public UrlResponse(String shortUrl) {
            this(shortUrl, null);
        }
    }
}


