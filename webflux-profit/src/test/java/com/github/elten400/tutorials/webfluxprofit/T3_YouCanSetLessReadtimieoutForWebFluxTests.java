package com.github.elten400.tutorials.webfluxprofit;

import com.github.elten400.tutorials.webfluxprofit.config.ClientApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static com.github.elten400.tutorials.webfluxprofit.ConsolePrint.printClientError;

/**
 * You should know.
 * readTimeout: is interval between each received data.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class T3_YouCanSetLessReadtimieoutForWebFluxTests {
    @Autowired
    ClientApiService clientApiService;

    /**
     * Old approach API.
     * Will throw Exception. Because service will write response after 6+8+9=23sec.
     */
    @Test
    void blockedService() {
        System.out.println("\tBLOCKED SERVICE REQUEST STARTED");
        try {
            clientApiService.callUri("/blocked-service?idList=6,8,5", 9);
        } catch (Throwable t) {
            printClientError(t.getMessage());
        }
    }

    /**
     * Reactive approach API.
     * Will receive all responses successfully.
     */
    @Test
    void reactiveStreamService() {
        System.out.println("\tREFLECTIVE STREAM REQUEST STARTED");

        try {
            clientApiService.callUri("/reactive-stream-service?idList=6,8,5", 9);
        } catch (Throwable t) {
            printClientError(t.getMessage());
        }
    }
}
