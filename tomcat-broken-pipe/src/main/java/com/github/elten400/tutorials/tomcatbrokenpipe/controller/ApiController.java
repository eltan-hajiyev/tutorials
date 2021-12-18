package com.github.elten400.tutorials.tomcatbrokenpipe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class ApiController {
    Logger log = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/info")
    public ArrayList getInfo() throws InterruptedException, IOException {
        log.warn(">>> method start");

        TimeUnit.SECONDS.sleep(9);

        log.warn(">>> method end");

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 1500; i++) {
            arrayList.add("Hello World\n");
        }

        return arrayList;
    }

}
