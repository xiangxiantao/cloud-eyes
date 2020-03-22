package com.xxt.springcloud.controller;

import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.loadbalance.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //通过restTemplate调用支付服务
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        //通过restTemplate调用支付服务
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //以下方法要能成功运行，需将RestTemplate申明bean的方法上的@LoadBalanced方法注释掉
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb(){
        //获取某个服务下面的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null||instances.size()<=0){
            log.warn("没有服务调用方");
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        log.info("/consumer/payment/lb,url:{}",serviceInstance.getUri());
        return restTemplate.getForObject(serviceInstance.getUri()+"/payment/lb",String.class);
    }

}
