package com.github.elten400.tutorials.webfluxprofit.controller;

import com.github.elten400.tutorials.webfluxprofit.dto.KeyPair;
import com.github.elten400.tutorials.webfluxprofit.repository.StudentRepository;
import com.github.elten400.tutorials.webfluxprofit.service.KeyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    KeyPairService userService;

    @GetMapping(value = "/blocked-service")
    public List<KeyPair> blockedService(Integer[] idList) {
        return Stream
                .of(idList)
                .map(v -> userService.getKey(v))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/reactive-stream-service", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<KeyPair> reactiveStreamService(Integer[] idList) {
        System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
        return Flux
                .fromArray(idList)
                .map(v -> userService.getKey(v));
    }


    @GetMapping(value = "/reactive-stream-mono2", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono mono2(Integer[] idList) {
        System.out.println("::::2");

        return Mono.zip(Mono.just(1).map(f -> userService.getKey(1)),
                Mono.just(1).map(f -> userService.getKey(2)))
                .doOnSuccess(s -> {
                    System.out.println(":::");
                })

                .map(v -> userService.getKey(3));
    }

//    @Autowired
//    private StudentRepository studentRepository;
//
//    @GetMapping(value = "/reactive-stream-mono3", produces = MediaType.APPLICATION_NDJSON_VALUE)
//    public Mono mono3(Integer[] idList) {
//        System.out.println("::::2");
//
//        return Mono.zip(studentRepository.findById(1), studentRepository.findById(2))
//                .flatMap(v -> {
//                    System.out.println(v.getT1());
//                    System.out.println(v.getT2());
//                    return Mono.empty();
//                });
//    }
}
