package com.example.PostProxy.core.system.dto;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {
    @Expose
    HashMap<String,String> params;
    @Expose
    String url;
}
