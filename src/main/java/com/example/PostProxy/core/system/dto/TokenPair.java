package com.example.PostProxy.core.system.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class TokenPair implements Serializable {
    @Expose
    String key;
    @Expose
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenPair(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
