package com.shortener.service;

import com.shortener.service.dto.ShortnerRequest;

public interface ShortnerService {

    String getShortenUrl(ShortnerRequest request);

    String getLongUrl(String shortUrl) throws Exception;
}
