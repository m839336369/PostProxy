package com.example.cureapi.core.system.controller;

import com.example.cureapi.base.BaseController;
import com.example.cureapi.base.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@RequestMapping("/question")
@RestController
public class UserController extends BaseController {


    @RequestMapping("/query")
    public Result queryAll(){
        return null;
    }

}