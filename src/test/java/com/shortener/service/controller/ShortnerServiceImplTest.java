package com.shortener.service.controller;

import com.shortener.service.ShortnerServiceImpl;
import com.shortener.service.dto.ShortnerRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShortnerServiceImplTest {

    private ShortnerServiceImpl service;

    @Before
    public void setup() {
        service = new ShortnerServiceImpl();
    }

    @Test
    public void shouldGenerateNewShortUrl() {
        final ShortnerRequest request = new ShortnerRequest();
        request.setClientId("clientId");
        request.setUrl("url");

        String result1 = service.getShortenUrl(request);
        Assert.assertNotNull(result1);

        request.setUrl("url2");
        String result2 = service.getShortenUrl(request);
        Assert.assertNotEquals(result1, result2);
    }

    @Test
    public void shouldReturnSameShortUrlForSameRequest() {
        final ShortnerRequest request = new ShortnerRequest();
        request.setClientId("clientId");
        request.setUrl("url");

        String result1 = service.getShortenUrl(request);
        Assert.assertNotNull(result1);

        String result2 = service.getShortenUrl(request);
        Assert.assertEquals(result1, result2);
    }

    @Test
    public void testGetLongUrl() throws Exception {
        // Setup
        String expectedLongUrl = "url";
        final ShortnerRequest request = new ShortnerRequest();
        request.setClientId("clientId");
        request.setUrl(expectedLongUrl);

        String shortUrl = service.getShortenUrl(request);
        // Run the test
        final String actual = service.getLongUrl(shortUrl);

        // Verify the results
        assertEquals(expectedLongUrl, actual);
    }

    @Test(expected = Exception.class)
    public void shouldCribIfShortUrlNotPresent() throws Exception {
        service.getLongUrl("shortUrl");
    }
}
