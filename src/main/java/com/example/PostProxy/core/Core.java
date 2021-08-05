package com.example.PostProxy.core;

import com.example.PostProxy.core.system.dto.Config;
import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;

public class Core {
    public static Config Config;
    public static GsonJsonParser GsonJsonParser = new GsonJsonParser();
    public static Gson Gson = new Gson();
}
