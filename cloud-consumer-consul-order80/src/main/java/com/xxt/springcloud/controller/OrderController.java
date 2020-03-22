package com.xxt.springcloud.controller;

import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://cloud-payment-service-consul";

    @Autowired
    RestTemplate restTemplate;

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

}
