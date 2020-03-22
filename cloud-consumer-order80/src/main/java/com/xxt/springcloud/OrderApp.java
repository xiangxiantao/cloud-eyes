package com.xxt.springcloud;

import com.xxt.loadbanlance.rule.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


@SpringBootApplication
@EnableEurekaClient
//下面表示当调用“CLOUD-PAYMENT-SERVICE”服务时，采用MyRuleConfig中设置的负载均衡策略
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRuleConfig.class)
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
