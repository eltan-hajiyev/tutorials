package com.github.elten400.tutorials.log4jjndibug.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiControllerr {
    Logger log = LogManager.getLogger(ApiControllerr.class);

    /**
     * Ldap JNDI injection will slow down this method
     */
    @GetMapping("/infected-method")
    public void infectedMethod(@RequestParam String cardNumber) {
        log.warn("infectedMethod start");
        //we will send: cardNumber=${jndi:ldap://localhost:8082/api/long-wait}
        log.info("Client request: " + cardNumber);
        log.warn("infectedMethod end");
    }

}
