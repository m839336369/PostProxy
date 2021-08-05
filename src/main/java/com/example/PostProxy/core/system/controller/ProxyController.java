package com.example.PostProxy.core.system.controller;

import com.example.PostProxy.base.BaseController;
import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.TokenPair;
import com.example.PostProxy.core.util.Http;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
        for (TokenPair token : Core.Config.getTokens()){
           body = body.replace(token.getKey(),token.getValue());
        }
        return Http.Post(request,body);
    }
}