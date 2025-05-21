package com.origin.urlShortener.UrlShortener.exception;

public class InvalidUrlException extends RuntimeException{

    public InvalidUrlException(String message) {
        super(message);
    }
}
