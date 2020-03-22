package com.xxt.springcloud.loadbalance.impl;

import com.xxt.springcloud.loadbalance.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个轮询的负载均衡策略
 */
@Component
public class MyLoadBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //CAS+自旋锁
    public final int getAndIncrement() {
        int current;
        int next;
        for (; ; ) {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
            if (atomicInteger.compareAndSet(current,next))
                return next;
        }
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index=getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
