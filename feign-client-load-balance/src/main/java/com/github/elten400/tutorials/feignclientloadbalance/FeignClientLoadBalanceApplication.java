package com.github.elten400.tutorials.feignclientloadbalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignClientLoadBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientLoadBalanceApplication.class, args);
    }

}
