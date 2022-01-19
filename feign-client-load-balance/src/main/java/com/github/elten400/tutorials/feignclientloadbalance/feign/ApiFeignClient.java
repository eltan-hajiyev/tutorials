package com.github.elten400.tutorials.feignclientloadbalance.feign;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test-feign-service")
@LoadBalancerClient(name = "test-feign-service")
public interface ApiFeignClient {
    @GetMapping("/info")
    void info();
}
