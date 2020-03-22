package com.xxt.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class,args);
    }
}
