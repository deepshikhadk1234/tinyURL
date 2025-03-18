package com.pideep.tinyurl.service;

public interface URLServiceInterface {
    String generateShortURL(String longURL) throws IllegalArgumentException;
    String retrieveLongURL(String shortURL) throws IllegalArgumentException;
}
