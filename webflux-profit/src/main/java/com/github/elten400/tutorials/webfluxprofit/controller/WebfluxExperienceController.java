package com.github.elten400.tutorials.webfluxprofit.controller;

import com.github.elten400.tutorials.webfluxprofit.model.Student;
import com.github.elten400.tutorials.webfluxprofit.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WebfluxExperienceController {
    @GetMapping("/g")
    public Flux g1() {
        return Flux.just(345).expand(v -> {
            try {
                System.out.println("::1:: ");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Mono.create(s -> {
                System.out.println("::2:: " + v);
            });
        });
//        Mono.create(v -> System.out.println(""))
//                .subscribe();
    }

    /**
     * Flux to mono
     */
    @GetMapping("/s")
    public Mono ss() {
        return Flux.just(Mono.just(234), Flux.just(234))
                .buffer()
                .single();
    }

    @GetMapping("/d")
    public void dd() {
        Mono.zip(Mono.empty(), Mono.empty())
                .flatMap(tuple -> {
                    Mono.zip(Mono.just(tuple.getT1()), Mono.just(tuple.getT2()), Mono.just("dsf"))
                            .subscribe(triple -> {
                                triple.equals("sdf");
                            });

                    return Mono.just("").flatMap(f -> {

                        return null;
                    });
                })
                .subscribe(tuple -> {

                });

    }


    @GetMapping("/u")
    public Flux<String> uu() {
        return Flux.fromStream(Stream.of("1", "2"))
                .doOnNext(c -> {
                    log.debug("Hello");
                })
                .log()
                .mergeWith(webClient.get().uri("/v2/pet/1")
                        .retrieve()
                        .bodyToFlux(String.class)
                        .log()
                        .publishOn(Schedulers.fromExecutor(taskExecutor))
                );
    }

    @GetMapping("/m")
    public Mono<Student> mm() {
        return Mono.zip(studentRepository.findById(1).log().doOnNext(v -> {
                            System.out.println(":::1");
                        }),
                        webClient.get().uri("/read-timeout-simulator/ss#s=1")
                                .retrieve()
                                .bodyToMono(String.class)
                                .map(v -> new Student())
                                .log().doOnNext(v -> {
                                    System.out.println(":::2");
                                }),
                        studentRepository.findById(2).log().doOnNext(v -> {
                            System.out.println(":::3");
                        }))
                .map(v -> v.getT1());
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping(value = "/t", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<String> tt() {
        System.out.println(":incget::" + atomicInteger.incrementAndGet());

        return Flux.mergeDelayError(3,
                webClient.get().uri("/read-timeout-simulator/ss#s=1")
                        .retrieve()
                        .bodyToMono(String.class),
                webClient.get().uri("/read-timeout-simulator/ss#s=2")
                        .retrieve()
                        .bodyToMono(String.class)
                , webClient.get().uri("/read-timeout-simulator/ss#s=3")
                        .retrieve()
                        .bodyToMono(String.class));
    }

    private final TaskExecutor taskExecutor;
    private final WebClient webClient;
    private final StudentRepository studentRepository;
}
