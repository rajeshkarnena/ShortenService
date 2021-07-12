package com.shortener.service.controller;

import com.shortener.service.ShortnerService;
import com.shortener.service.dto.ShortnerRequest;
import com.shortener.service.dto.ShortnerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class ShortnerController {

    private ShortnerService shortnerService;

    public ShortnerController(ShortnerService shortnerService) {
        this.shortnerService = shortnerService;
    }

    @PostMapping("/shorten")
    public ShortnerResponse shortenUrl(@RequestBody ShortnerRequest request) {

        // validate req
        if (!isValid(request)) {
            ShortnerResponse response = new ShortnerResponse();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError("Request body has some empty params");
            return response;
        }

        String shortUrl = shortnerService.getShortenUrl(request);

        ShortnerResponse response = new ShortnerResponse();
        response.setShortUrl(shortUrl);
        response.setStatus(HttpStatus.OK.value());
        return response;
    }


    private boolean isValid(ShortnerRequest request) {
        return (Objects.nonNull(request) && !StringUtils.isEmpty(request.getClientId()) && !StringUtils.isEmpty(request.getUrl()));
    }
}
