package com.example.PostProxy.core.system.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Config implements Serializable {
    @Expose
    private String url = null;
    @Expose
    private List<TokenPair> tokens = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TokenPair> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenPair> tokens) {
        this.tokens = tokens;
    }
}
