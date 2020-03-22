package com.xxt.loadbanlance.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MySelfRule extends AbstractLoadBalancerRule {

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    private final int getIncrementInt() {
        for (; ; ) {
            int current = atomicInteger.get();
            int next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (atomicInteger.compareAndSet(current, next))
                return next;
        }
    }

    @Override
    public Server choose(Object o) {
        return choose(getLoadBalancer(), o);
    }

    public Server choose(ILoadBalancer loadBalancer, Object key) {
        List<Server> reachableServers = loadBalancer.getReachableServers();
        log.info("当前可用服务数：{}", reachableServers.size());
        int val = getIncrementInt();
        log.info("当前val:{}", val);
        //当val为2的次幂时
        if ((val & -val) == val) {
            return reachableServers.get(1);
        } else {
            return reachableServers.get(0);
        }
    }


}
