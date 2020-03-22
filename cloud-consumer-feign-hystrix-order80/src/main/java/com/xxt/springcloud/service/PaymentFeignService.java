package com.xxt.springcloud.service;

import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.service.fallback.PaymentFeignFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFeignFallBackService.class)
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/longTimeJob")
    public String longTimeJob();

    @GetMapping(value = "/payment/ping")
    public String ping();

}
