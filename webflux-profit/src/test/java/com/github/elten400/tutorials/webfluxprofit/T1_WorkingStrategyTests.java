package com.github.elten400.tutorials.webfluxprofit;

import com.github.elten400.tutorials.webfluxprofit.config.ClientApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class T1_WorkingStrategyTests {

    @Autowired
    ClientApiService clientApiService;

    @Test
    /**
     * Old approach API.
     * Client will receive response after complete execution.
     */
    void blockedService() {
        System.out.println("\tBLOCKED SERVICE REQUEST STARTED");
        clientApiService.callUri("/blocked-service?idList=6,3,5", 17);
    }

    /**
     * Reactive approach API.
     * Client will receive response partially for each executed process (Event) .
     */
    @Test
    void reactiveStreamService() {
        System.out.println("\tREFLECTIVE STREAM REQUEST STARTED");
        clientApiService.callUri("/reactive-stream-service?idList=6,3,5", 17);
    }
}
