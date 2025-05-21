package com.origin.urlShortener.UrlShortener.service;

import com.origin.urlShortener.UrlShortener.entity.UrlMapping;
import com.origin.urlShortener.UrlShortener.exception.InvalidUrlException;
import com.origin.urlShortener.UrlShortener.exception.UrlNotFoundException;
import com.origin.urlShortener.UrlShortener.util.Base62;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/***
 * Service that handles the core logic
 * - Generates unique, short, non-sequential codes for long urls
 * - Stores and retrieves URL mappings using in-memory store
 * - validates input urls
 * - handles Exceptions
 * @author sudar
 */
@Service
public class UrlService {


    /***
     * Uses ConcurrentHashMap for thread-safe access and updates,
     * allows concurrent read/write operations
     */
    private final Map<String, UrlMapping> urlStore = new ConcurrentHashMap<>();

    public String shortenUrl(String url) throws MalformedURLException {
        validateUrl(url);
        String code = generateShortCode(url);
        urlStore.putIfAbsent(code, new UrlMapping(code, url, LocalDateTime.now()));
        return code;
    }

    public UrlMapping getOriginalUrl(String code) {
       UrlMapping mapping = urlStore.get(code);
        if(mapping == null) {
            throw new UrlNotFoundException(code);
        }
        return mapping;
    }

    private void validateUrl(String url) throws MalformedURLException {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL format");
        }
    }
    private String generateShortCode(String url) {
        return Base62.encode(UUID.randomUUID().toString().hashCode());
    }

}
