package com.github.elten400.tutorials.webfluxprofit.service;

import com.github.elten400.tutorials.webfluxprofit.dto.KeyPair;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.github.elten400.tutorials.webfluxprofit.ConsolePrint.printServer;

@Service
public class KeyPairService {
    public KeyPair getKey(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        KeyPair keyPair = KeyPair.of("key", "value" + id);
        printServer("server returns: " + keyPair);
        return keyPair;
    }
}
