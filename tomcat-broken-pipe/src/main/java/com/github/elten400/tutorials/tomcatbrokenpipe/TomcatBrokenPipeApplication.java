package com.github.elten400.tutorials.tomcatbrokenpipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TomcatBrokenPipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatBrokenPipeApplication.class, args);
    }

}
