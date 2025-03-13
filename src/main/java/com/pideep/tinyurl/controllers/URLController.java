package com.pideep.tinyurl.controllers;

import com.pideep.tinyurl.model.URLMapping;
import com.pideep.tinyurl.service.URLService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class URLController {
    @Autowired
    private URLService urlService;


    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> generateShortURL(
            @RequestBody  URLMapping request) {
        try{
            String shortURL = urlService.generateShortURL(request.getLongURL());
            return ResponseEntity.ok(new UrlResponse(shortURL));
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new UrlResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<URLMapping> retrieveLongURL(@PathVariable String shortURL) {
        try{
            String longURL = urlService.retrieveLongURL(shortURL);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longURL)).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    public record UrlResponse(
            String shortUrl,
            String errorMessage
    ) {
        public UrlResponse(String shortUrl) {
            this(shortUrl, null);
        }
    }


}
