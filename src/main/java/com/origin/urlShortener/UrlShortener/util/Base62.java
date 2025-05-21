package com.origin.urlShortener.UrlShortener.util;


/***
 * Utility class for encoding and decoding numeric values using Base62
 * Base62 consists of characters [0-9][A-Z][a-z] suitable for generating URL-safe short codes
 */
public class Base62 {

    private static final String ALPHANUMERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = ALPHANUMERICS.length();


    public static String encode(long num) {

        if(num == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while(num>0) {
            sb.append(ALPHANUMERICS.charAt((int)(num%BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

}
