package com.example.PostProxy.core.system.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class Config {
    private HashMap<String,Token> tokens = null;
}
