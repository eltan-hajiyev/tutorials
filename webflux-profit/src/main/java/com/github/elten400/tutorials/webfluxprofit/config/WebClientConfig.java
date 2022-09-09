package com.github.elten400.tutorials.webfluxprofit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader("accept", "application/json")
                .defaultHeader("User-Agent", "Chrome")
                .build();
    }

//    @Bean
//    public ThreadPoolTaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(100);
//        executor.setThreadNamePrefix("slow-");
//        executor.initialize();
//        return executor;
//    }

}
