package com.example.PostProxy.core.system.dto;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Token implements Serializable {
    @Expose
    String url;
    @Expose
    HashMap<String,String> params;
    @Expose
    ArrayList<ResultProcess> resultProcesses = new ArrayList<>();
    @Expose
    HashMap<String,String> headers;

    public Token(HashMap<String, String> params,HashMap<String,String> headers,String url) {
        this.url = url;
        this.params = params;
        this.headers = headers;
    }
}
