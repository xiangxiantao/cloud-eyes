package com.xxt.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 自定义路由规则
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes=routeLocatorBuilder.routes();
        routes.route("baidu_route_guonei",predicateSpec -> predicateSpec.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        routes.route("baidu_route_guoji",predicateSpec -> predicateSpec.path("/guoji").uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }

}
