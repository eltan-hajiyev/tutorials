package com.github.elten400.tutorials.webfluxprofit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestConfig {
    List<Object> arr = new ArrayList<>();

    @Bean
    public WebClient webClient() {
        String url = "http://localhost:9081/api";
        return WebClient
                .builder()
                .baseUrl(url)
                .build();
    }
}
