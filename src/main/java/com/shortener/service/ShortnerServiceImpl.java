package com.shortener.service;

import com.shortener.service.dto.ShortnerRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ShortnerServiceImpl implements ShortnerService {

    /**
     * clientId, longUrl --> shortUrl
     * shortUrl --> longUrl
     */
    /**
     * SHORTURL
     * short - id (PK, UNIQUE, )
     * clientId - PARTITION
     * LONG URL -
     */


    /**
     * th1 -> a  -> 1
     * th2 -> a
     */

    private static final String alphabets = "0123456789a-zA-Z";
    private final AtomicInteger counter = new AtomicInteger(0);
    private Map<ShortnerRequest, String> urlReqToShortUrlMap = new HashMap<>();
    private Map<String, String> shortUrlToUrlMap = new HashMap<>();

    @Override
    public String getShortenUrl(ShortnerRequest request) {
        if (urlReqToShortUrlMap.containsKey(request)) {
            return urlReqToShortUrlMap.get(request);
        }

        String shortUrl = String.valueOf(counter.getAndIncrement());

        urlReqToShortUrlMap.put(request, shortUrl);
        shortUrlToUrlMap.put(shortUrl, request.getUrl());

        return shortUrl;
    }

    @Override
    public String getLongUrl(String shortUrl) throws Exception {
        String longUrl = shortUrlToUrlMap.get(shortUrl);

        if (Objects.isNull(longUrl)) {
            throw new Exception("Unknown short Url");
        }

        return longUrl;
    }
}
