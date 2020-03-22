package com.xxt.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//开启配置中心
@EnableConfigServer
public class ConfigCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApp.class, args);
    }
}
