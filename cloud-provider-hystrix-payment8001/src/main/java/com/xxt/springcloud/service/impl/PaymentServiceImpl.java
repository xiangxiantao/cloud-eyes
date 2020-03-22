package com.xxt.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.dao.PaymentDao;
import com.xxt.springcloud.service.PaymentService;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public String ping() {
        return "线程池：" + Thread.currentThread().getName() + "ping OK";
    }

    /*================================服务降级=============================================*/
    @Override
    //一般不在服务的提供方进行服务降级
/*    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            //超时或者抛出异常都会降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")
    })*/
    public String longTimeJob() {
        int age = 10 / 0;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "|on port:";
    }


    public String timeoutHandler() {
        return "原耗时服务超时，返回兜底信息";
    }

    /*================================服务熔断=============================================*/

    //总体解释：当断路器为开启时，在sleepWindowInMilliseconds所规定的时间内，达到了requestVolumeThreshold次请求，
    //且失败率在errorThresholdPercentage以上，则熔断
    @HystrixCommand(fallbackMethod = "paymentCitcuitBreaker_fallback",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //跳闸的失败率
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    @Override
    public String paymentCitcuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("***********id不能为负数");
        }

        String serialNo =IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用paymentCitcuitBreaker成功，流水号：" + serialNo;
    }

    public String paymentCitcuitBreaker_fallback(Integer id){
        return Thread.currentThread().getName() + "\t" + "调用paymentCitcuitBreaker失败，id不能为复数";
    }


}
