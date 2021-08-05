package com.example.PostProxy;

import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.Config;
import com.example.PostProxy.core.system.dto.TokenPair;
import com.example.PostProxy.core.util.ConfigHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.util.ArrayList;

@ComponentScan(value = "com.example.PostProxy.core.system.controller")
@SpringBootApplication
public class PostProxyApiApplication {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir+ "/ProxyConfig");
        if(!file.exists()){
            System.out.println("未找到Config配置项,已自动生成默认");
            System.out.println("写入：" + userDir + "/ProxyConfig");
            Config config = new Config();
            config.setUrl("http://localhost:80/proxy/test");
            config.setTokens(new ArrayList<>());
            config.getTokens().add(new TokenPair("12312","asdd"));
            config.getTokens().add(new TokenPair("12","ss"));
            config.getTokens().add(new TokenPair("22","asdd"));
            config.getTokens().add(new TokenPair("12211312","asdd"));
            ConfigHelper.Save(config);
            Core.Config = config;
        }
        else {
            System.out.println("读取：" + userDir + "/ProxyConfig");
            Core.Config = ConfigHelper.Load();
        }
        if(Core.Config == null){
            System.out.println(file.getPath());
            System.out.println("Config文件错误");
            return;
        }
        SpringApplication.run(PostProxyApiApplication.class, args);
    }
}
