package com.example.PostProxy.core.system.controller;

import com.example.PostProxy.base.BaseController;
import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.TokenPair;
import com.example.PostProxy.core.util.Http;
import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@RequestMapping("/proxy")
@RestController
public class ProxyController extends BaseController {

    @RequestMapping (value = "/test",method = RequestMethod.POST)
    public String Test(@RequestBody String request){
        return "d";
    }

    @RequestMapping (value = "/post",method = RequestMethod.POST)
    public String Post(HttpServletRequest request,@RequestBody String body){
        boolean flag = false;
        Map objects = Core.GsonJsonParser.parseMap(body);
        Object token_obj = objects.get("token");
        String value = "";
        if(token_obj!=null)value = token_obj.toString();
        for (TokenPair token : Core.Config.getTokens()){
            if(value.equals(token.getKey())){
                flag = true;
                objects.replace("token",token.getValue());
                break;
            }
        }
        if(flag){
            return Http.Post(request, Core.Gson.toJson(objects));
        }
        else {
            return "{\n" +
                    "\t\"code\": 1,\n" +
                    "\t\"message\": \"任务不存在\",\n" +
                    "\t\"data\": null\n" +
                    "}";
        }
    }
}