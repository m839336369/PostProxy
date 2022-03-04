package com.example.PostProxy.core.util;

import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.Token;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

public class Http {
    public static String getResult(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
