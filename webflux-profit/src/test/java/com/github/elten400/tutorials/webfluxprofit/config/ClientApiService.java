package com.github.elten400.tutorials.webfluxprofit.config;

import com.github.elten400.tutorials.webfluxprofit.dto.KeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

import static com.github.elten400.tutorials.webfluxprofit.ConsolePrint.printClient;

@Service
public class ClientApiService {
    @Autowired
    WebClient webClient;

    public void callUri(String uri, Integer readTimeout) {
        System.out.println(" readTimeout: " + readTimeout);

        Flux<KeyPair> flux = webClient.get()
                .uri(uri)
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(readTimeout));
                })
                .accept(MediaType.APPLICATION_NDJSON)
                .retrieve()
                .bodyToFlux(KeyPair.class)
                //.timeout(Duration.ofSeconds(8))
                ;

        flux.all(v -> {
            printClient("client accepts: " + v);
            return true;
        }).block();
    }
}
