package com.xxt.springcloud.controller;


import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Value("${server.port}")
    private String port;

    /**
     * 说明：注意@ResponseBody注解，如果入参加了这个注解，请求中需要将请求报文以json字符串的形式传入
     *                            如果入参不加这个注解，请求中将请求报文以表单数据或者路径参数传入
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功,port:"+port, payment);
        } else {
            return new CommonResult(444, "插入失败,port:"+port, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("查询结果:" + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功 ,port:" + port, paymentById);
        } else {
            return new CommonResult(444, "没有对应记录，查询id:" + id + "port:" + port, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){

        //获取所有服务
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("****element: " +element);
        }

        //获取某个服务下面的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getLb(){
        return port;
    }

}
