package com.github.elten400.tutorials.log4jjndibug;

import com.github.elten400.tutorials.log4jjndibug.simulate.LdapSocketSimulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Log4jJndiBugApplication {

    public static void main(String[] args) {
        SpringApplication.run(Log4jJndiBugApplication.class, args);
    }


    @PostConstruct
    public void startLdapSimulator() throws Exception {
        new LdapSocketSimulator(8082, TimeUnit.SECONDS.toMillis(5)).start();
    }

}
