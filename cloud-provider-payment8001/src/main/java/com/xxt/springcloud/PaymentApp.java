package com.xxt.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan(basePackages = {"com.xxt.springcloud.dao"})
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentApp {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class, args);
    }
}
