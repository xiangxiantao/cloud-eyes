package com.xxt.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallBack")
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }


    @GetMapping(value = "/consumer/payment/longTimeJob")
    //通过fallbackMethod定制服务降级方法
/*    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            //超时或者抛出异常都会降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")
    })*/

    //不指定fallbackMethod，采用全局的服务降级方法
    @HystrixCommand
    public String longTimeJob() {
        int i=10/0;
        return paymentFeignService.longTimeJob();
    }

    @GetMapping(value = "/consumer/payment/ping")
    public String ping() {
        return paymentFeignService.ping();
    }

    public String timeoutHandler() {
        return "哦豁 请求超时";
    }

    //全局fallback
    public String globalFallBack() {
        return "global异常";
    }


}
