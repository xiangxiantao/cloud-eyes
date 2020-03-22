package com.xxt.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    //默认负载均衡的策略
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
