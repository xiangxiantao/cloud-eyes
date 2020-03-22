package com.xxt.loadbanlance.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRuleConfig {

    @Bean
    public IRule myRule(){
        //return new RandomRule();//随机（默认轮询）
        return new MySelfRule();
    }
}
