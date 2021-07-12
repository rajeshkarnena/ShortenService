package com.shortener.service.dto;

import java.util.Objects;

public class ShortnerRequest {
    private String clientId;
    private String url;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortnerRequest that = (ShortnerRequest) o;
        return clientId.equals(that.clientId) && url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, url);
    }
}
