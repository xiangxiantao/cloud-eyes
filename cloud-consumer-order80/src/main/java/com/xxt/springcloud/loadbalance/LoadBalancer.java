package com.xxt.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
