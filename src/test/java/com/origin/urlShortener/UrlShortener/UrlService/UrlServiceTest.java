package com.origin.urlShortener.UrlShortener.UrlService;

import com.origin.urlShortener.UrlShortener.entity.UrlMapping;
import com.origin.urlShortener.UrlShortener.exception.InvalidUrlException;
import com.origin.urlShortener.UrlShortener.exception.UrlNotFoundException;
import com.origin.urlShortener.UrlShortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @InjectMocks
    private UrlService urlService;


    @Test
    void testShortenUrl_validUrl_shouldReturnCode() throws MalformedURLException {
        String longUrl = "https://www.google.com";
        String code = urlService.shortenUrl(longUrl);

        assertNotNull(code);

        UrlMapping mapping = urlService.getOriginalUrl(code);
        assertEquals(longUrl, mapping.getOriginalUrl());
        assertEquals(code, mapping.getShortCode());
        assertNotNull(mapping.getCreatedAt());

    }

    @Test
    void testGetOriginalUrl_NotFound_shouldThrowException() {
        String nonExistentCode = "notexistent";

        UrlNotFoundException exception = assertThrows(UrlNotFoundException.class, () -> {
            urlService.getOriginalUrl(nonExistentCode);
        });

        assertTrue(exception.getMessage().contains(nonExistentCode));

    }

    @Test
    void testShortenUrl_InvalidUrl_ShouldThrowException() {
        String invalidUrl = "htp:/invalid-url";

        InvalidUrlException exception = assertThrows(InvalidUrlException.class, () -> {
            urlService.shortenUrl(invalidUrl);
        });

        assertEquals("Invalid URL format", exception.getMessage());
    }
}
