package com.origin.urlShortener.UrlShortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String code) {
        super("No URL mapping found for code: " + code);
    }

}
