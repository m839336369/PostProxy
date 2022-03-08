package com.example.PostProxy.core.system.controller;

import com.example.PostProxy.base.BaseController;
import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.ResultProcess;
import com.example.PostProxy.core.system.dto.Token;
import com.example.PostProxy.core.util.Http;
import com.google.gson.JsonObject;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@RestController
public class ProxyController extends BaseController {
    @PostMapping(value = "/request")
    public String Post(HttpServletRequest request,@RequestBody String body) throws IOException {
        Map<String,Object> objects = Core.GsonJsonParser.parseMap(body);
        Object token_obj = objects.get("token");
        if(token_obj!=null || !Core.Config.getTokens().containsKey(token_obj.toString())){
            String value = token_obj.toString();
            Token token = Core.Config.getTokens().get(value);
            Request.Builder builder = new Request.Builder();
            builder.url(token.getUrl());
            Map<String,Object> request_body = new HashMap<>(token.getParams());
            for (String key : objects.keySet()){
                if(!request_body.containsKey(key)){
                    request_body.put(key,objects.get(key));
                }
            }
            builder.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .post(okhttp3.RequestBody.create(Core.Gson.toJson(request_body).getBytes(StandardCharsets.UTF_8)));
            for (Map.Entry<String,String> head : token.getHeaders().entrySet()){
                builder.header(head.getKey(),head.getValue());
            }
            String result = Http.getResult(builder.build());
            for (ResultProcess process : token.getResultProcesses()){
                result = process.process(result);
            }
            return result;
        }
        return "token有误，请传递正确token";
    }

    @GetMapping(value = "/request")
    public String Get(HttpServletRequest request) throws IOException {
        Object token_obj = request.getParameter("token");
        if(token_obj!=null){
            String value = token_obj.toString();
            Token token = Core.Config.getTokens().get(value);
            if(token != null || !Core.Config.getTokens().containsKey(token_obj.toString())){
                Request.Builder builder = new Request.Builder();
                StringBuilder url = new StringBuilder(token.getUrl());
                if(!(token.getUrl().charAt(token.getUrl().length() -1) == '?')){
                    url.append("?");
                }
                for (String key : request.getParameterMap().keySet()){
                    if(url.charAt(url.length() -1) != '?'){
                        url.append("&");
                    }
                    if(!token.getParams().containsKey(key)){
                        url.append(key).append("=").append(request.getParameter(key));
                    }
                }
                for(String key : token.getParams().keySet()){
                    if(url.charAt(url.length() -1) != '?'){
                        url.append("&");
                    }
                    url.append(key).append("=").append(token.getParams().get(key));
                }
                builder.url(url.toString());
                for (Map.Entry<String,String> head : token.getHeaders().entrySet()){
                    builder.header(head.getKey(),head.getValue());
                }
                String result = Http.getResult(builder.build());
                for (ResultProcess process : token.getResultProcesses()){
                    result = process.process(result);
                }
                return result;
            }
        }
        return "token有误，请传递正确token";
    }
}