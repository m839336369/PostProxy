package com.example.PostProxy.core.system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class ResultProcess {
    public enum Operate{ Replace, Ratio}
    @Getter
    @Setter
    Operate operate = Operate.Replace;
    @Getter
    @Setter
    String regex;
    @Getter
    @Setter
    String value;
    private Pattern pattern;
    public String process(String result){
        if(result == null)return null;
        if(operate == Operate.Replace){
            result = result.replaceAll(regex,value);
        }
        else if (operate == Operate.Ratio){
            if(pattern == null)pattern = Pattern.compile(regex,Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()){
                double num = Double.parseDouble(matcher.group(1)) * Double.parseDouble(value);
                result = result.replace(matcher.group(0),matcher.group(0).replace(matcher.group(1),String.valueOf(num)));
            }
        }
        return result;
    }

    public ResultProcess(Operate operate, String regex, String value) {
        this.operate = operate;
        this.regex = regex;
        this.value = value;
    }
}
