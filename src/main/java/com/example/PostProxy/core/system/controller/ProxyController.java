package com.example.PostProxy.core.system.controller;

import com.example.PostProxy.base.BaseController;
import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.ResultProcess;
import com.example.PostProxy.core.system.dto.Token;
import com.example.PostProxy.core.util.Http;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

//    @PostMapping(value = "/json")
//    public String JsonTest(HttpServletRequest request){
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("balance","123");
//        jsonObject.addProperty("id","12312");
//        jsonObject.addProperty("name","xiaoming");
//        return Core.Gson.toJson(jsonObject);
//    }
//
//    @PostMapping(value = "/string")
//    public String StringTest(HttpServletRequest request){
//        return "123";
//    }

    @PostMapping(value = "/request")
    public String Post(HttpServletRequest request,@RequestBody String body){
        Map<String,Object> objects = Core.GsonJsonParser.parseMap(body);
        Object token_obj = objects.get("token");
        if(token_obj!=null){
            String value = token_obj.toString();
            Token token = Core.Config.getTokens().get(value);
            Map<String,Object> request_body = new HashMap<>(token.getParams());
            for (String key : objects.keySet()){
                if(!request_body.containsKey(key)){
                    request_body.put(key,objects.get(key));
                }
            }
            String result = Http.Post(request,Core.Gson.toJson(request_body),token.getUrl());
            for (ResultProcess process : token.getResultProcesses()){
                result = process.process(result);
            }
            return result;
        }
        return "token有误，请传递正确token";
    }

    @GetMapping(value = "/request")
    public String Get(HttpServletRequest request){
        Object token_obj = request.getParameter("token");
        if(token_obj!=null){
            String value = token_obj.toString();
            Token token = Core.Config.getTokens().get(value);
            if(token != null){
                StringBuilder url = new StringBuilder(token.getUrl());
                if(!(token.getUrl().charAt(token.getUrl().length() -1) == '?')){
                    url.append("?");
                }
                for(String key : token.getParams().keySet()){
                    url.append("&").append(key).append("=").append(token.getParams().get(key));
                }
                for (String key : request.getParameterMap().keySet()){
                    if(!token.getParams().containsKey(key)){
                        url.append("&").append(key).append("=").append(request.getParameter(key));
                    }
                }
                String result = Http.Get(request,url.toString());
                for (ResultProcess process : token.getResultProcesses()){
                    result = process.process(result);
                }
                return result;
            }
        }
        return "token有误，请传递正确token";
    }
}