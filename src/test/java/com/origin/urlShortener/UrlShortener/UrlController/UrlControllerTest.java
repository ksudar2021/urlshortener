package com.origin.urlShortener.UrlShortener.UrlController;

import com.origin.urlShortener.UrlShortener.controller.UrlController;
import com.origin.urlShortener.UrlShortener.entity.UrlMapping;
import com.origin.urlShortener.UrlShortener.exception.UrlNotFoundException;
import com.origin.urlShortener.UrlShortener.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UrlService urlService;

    @InjectMocks
    private UrlController urlController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(urlController).build();
    }
    @Test
    void testShortenUrl() throws Exception {
        Mockito.when(urlService.shortenUrl(Mockito.anyString()))
                .thenReturn("abc123");

        mockMvc.perform(post("/url/shorten")
                        .param("url", "https://www.google.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("http://localhost:8080/url/abc123"));
    }

    @Test
    void testRedirect_Success() throws Exception {
        String shortCode = "abc1213";
        String originalUrl = "https://www.google.com";

        UrlMapping mapping = new UrlMapping(shortCode, originalUrl, LocalDateTime.now());
        Mockito.when(urlService.getOriginalUrl(shortCode)).thenReturn(mapping);

        mockMvc.perform(get("/url/{code}", shortCode))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", originalUrl));

    }

}
