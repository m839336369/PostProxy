package com.example.cureapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(value = "com.example.cureapi.core.system.controller")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class CureApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CureApiApplication.class, args);
    }


}
