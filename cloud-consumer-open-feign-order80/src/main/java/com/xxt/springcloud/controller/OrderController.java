package com.xxt.springcloud.controller;

import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
       return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/longTimeJob")
    public String longTimeJob(){
        return paymentFeignService.longTimeJob();
    }


}
