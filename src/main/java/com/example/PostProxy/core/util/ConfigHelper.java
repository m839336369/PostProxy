package com.example.PostProxy.core.util;

import com.example.PostProxy.core.system.dto.Config;
import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigHelper {
    public static void Save(Config config){
        try {
            String userDir = System.getProperty("user.dir");
            FileOutputStream writer = new FileOutputStream(userDir + "/ProxyConfig");
            String s = new Yaml().dump(config);
            writer.write(s.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Config Load(){
        try {
            String userDir = System.getProperty("user.dir");
            File file = new File(userDir + "/ProxyConfig");
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            return new Yaml().load(new String(filecontent, StandardCharsets.UTF_8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
