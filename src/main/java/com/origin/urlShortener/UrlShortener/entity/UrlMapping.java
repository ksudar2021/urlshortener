package com.origin.urlShortener.UrlShortener.entity;

import java.time.LocalDateTime;

/**
 * -shortCode: the generated short identifier
 * -originalUrl: Actual full url
 * -createdAt: timestamp when mapping is created
 */
public class UrlMapping {
    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private String shortCode;
    private String originalUrl;
    private LocalDateTime createdAt;

    public UrlMapping(String shortCode, String originalUrl, LocalDateTime createdAt) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }

}
