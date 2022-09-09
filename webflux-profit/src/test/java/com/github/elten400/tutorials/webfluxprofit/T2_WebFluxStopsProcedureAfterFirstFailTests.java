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
class T2_WebFluxStopsProcedureAfterFirstFailTests {
    @Autowired
    ClientApiService clientApiService;

    @Test
    /**
     * Old approach API.
     * All procedures will be executed despite connection closed by client.
     */
    void blockedService() {
        System.out.println("\tBLOCKED SERVICE REQUEST STARTED");
        try {
            clientApiService.callUri("/blocked-service?idList=6,19,5,7,6,2", 10);
        } catch (Throwable t) {
            printClientError(t.getMessage());
        }

    }

    /**
     * Reactive approach API.
     * Execution of procedures will be stopped after connection close.
     */
    @Test
    void reactiveStreamService() {
        System.out.println("\tREFLECTIVE STREAM REQUEST STARTED");
        try {
            clientApiService.callUri("/reactive-stream-service?idList=6,19,5,7,6,2", 10);
        } catch (Throwable t) {
            printClientError(t.getMessage());
        }

    }
}
