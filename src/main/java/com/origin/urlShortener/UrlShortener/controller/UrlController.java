package com.origin.urlShortener.UrlShortener.controller;

import com.origin.urlShortener.UrlShortener.entity.UrlMapping;
import com.origin.urlShortener.UrlShortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;


    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /***
     * post request handles the request shorten and store the url
     * @param url url to be shortened
     * @return response with new shortened url
     * @throws MalformedURLException when url format is invalid
     */


    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestParam String url) throws MalformedURLException {
        String code = urlService.shortenUrl(url);
        return ResponseEntity.ok("http://localhost:8080/url/"+code);
    }


    /***
     * gets short url requests and redirects to actual url
     * @param code url code from path variable
     * @return redirects to the actual url
     */
    @GetMapping("{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        UrlMapping mapping = urlService.getOriginalUrl(code);
        String url = mapping.getOriginalUrl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url)).build();
    }
}
