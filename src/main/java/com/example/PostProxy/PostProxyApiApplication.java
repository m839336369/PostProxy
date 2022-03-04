package com.example.PostProxy;

import com.example.PostProxy.core.Core;
import com.example.PostProxy.core.system.dto.Config;
import com.example.PostProxy.core.system.dto.ResultProcess;
import com.example.PostProxy.core.system.dto.Token;
import com.example.PostProxy.core.util.ConfigHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@ComponentScan(value = "com.example.PostProxy.core.system.controller")
@SpringBootApplication
public class PostProxyApiApplication {
    // sudo fuser -k -n tcp 8080
    // java -jar /usr/PostProxy/PostProxy.jar
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        File file = new File(userDir+ "/ProxyConfig");
        if(!file.exists()){
            System.out.println("未找到Config配置项,已自动生成默认");
            System.out.println("写入：" + userDir + "/ProxyConfig");
            Config config = new Config();
            config.setTokens(new HashMap<>());
            config.getTokens().put("Api2",new Token(new HashMap<>(),new HashMap<>(),"http://api2.ronsir.cn/v1/email/send"));
            config.getTokens().get("Api2").getParams().put("token","558ffa8177ac5748869c4cd1c93d57ab");
            config.getTokens().put("菜谱查询",new Token(new HashMap<>(),new HashMap<>(),"https://api.iyk0.com/shipu/"));
            config.getTokens().get("菜谱查询").getParams().put("key","白菜");
            config.getTokens().get("菜谱查询").getParams().put("p","1");
            config.getTokens().get("菜谱查询").getParams().put("n","1");
            config.getTokens().put("Ratio",new Token(new HashMap<>(),new HashMap<>(),"https://api.iyk0.com/sjzx/?msg=苹果12"));
            config.getTokens().get("Ratio").getResultProcesses().add(new ResultProcess(ResultProcess.Operate.Ratio,"\"描述价格\": \"(\\d*)\"","0.5"));
            config.getTokens().put("Replace",new Token(new HashMap<>(),new HashMap<>(),"https://api.iyk0.com/sjzx/?msg=苹果12"));
            config.getTokens().get("Replace").getResultProcesses().add(new ResultProcess(ResultProcess.Operate.Replace,"\"描述价格\": \"(\\d*)\"","\"描述价格\": \"99999\""));
            config.getTokens().put("AddTaskList",new Token(new HashMap<>(),new HashMap<>(),"http://qapi.shuzhikj.com/api/platform/add_tasklist"));
            config.getTokens().get("AddTaskList").getHeaders().put("apikey","sz202201118329xns");
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
