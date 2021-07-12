package com.shortener.service.controller;


import com.shortener.service.ShortnerService;
import com.shortener.service.controller.ShortnerController;
import com.shortener.service.dto.ShortnerRequest;
import com.shortener.service.dto.ShortnerResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ShortnerControllerTest {

    @InjectMocks
    private ShortnerController controller;

    @Mock
    private ShortnerService shortnerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(shortnerService.getShortenUrl(Mockito.any(ShortnerRequest.class))).thenReturn("test");
    }


    @Test
    public void shouldCribForEmptyClientId() {
        final ShortnerRequest request = new ShortnerRequest();
        request.setUrl("longurl");

        // Run the test
        final ShortnerResponse result = controller.shortenUrl(request);

        // Verify the results
        Mockito.verify(shortnerService, Mockito.never()).getShortenUrl(Mockito.eq(request));
        Assert.assertNull(result.getShortUrl());
        Assert.assertEquals(400, result.getStatus());
        Assert.assertNotNull(result.getError());
    }

    @Test
    public void shouldCribForEmptyUrl() {
        final ShortnerRequest request = new ShortnerRequest();
        request.setClientId("clientId");

        // Run the test
        final ShortnerResponse result = controller.shortenUrl(request);

        // Verify the results
        Mockito.verify(shortnerService, Mockito.never()).getShortenUrl(Mockito.eq(request));
        Assert.assertNull(result.getShortUrl());
        Assert.assertEquals(400, result.getStatus());
        Assert.assertNotNull(result.getError());
    }

    @Test
    public void testShortenUrl() {
        // Setup
        final ShortnerRequest request = new ShortnerRequest();
        request.setClientId("clientId");
        request.setUrl("url");

        // Run the test
        final ShortnerResponse result = controller.shortenUrl(request);

        // Verify the results
        Mockito.verify(shortnerService, Mockito.times(1)).getShortenUrl(Mockito.eq(request));
        Assert.assertEquals("test", result.getShortUrl());
        Assert.assertEquals(200, result.getStatus());
        Assert.assertNull(result.getError());
    }
}
