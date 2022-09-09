package com.github.elten400.tutorials.webfluxprofit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class WebfluxProfitApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxProfitApplication.class, args);
    }

    @Autowired
    private WebClient webClient;

    @Bean
    RouterFunction<ServerResponse> getEmployeeByIdRoute() {
        return route(GET("/employees"),
                req -> ok().body(Flux.merge(
                        webClient.get().uri("/read-timeout-simulator/ss#s=1")
                                .retrieve()
                                .bodyToMono(String.class),
                        webClient.get().uri("/read-timeout-simulator/ss#s=2")
                                .retrieve()
                                .bodyToMono(String.class)
                        ,webClient.get().uri("/read-timeout-simulator/ss#s=3")
                                .retrieve()
                                .bodyToMono(String.class)), String.class));
    }
}
