package com.github.elten400.tutorials.httptimeout.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author Eltan Hajiyev
 */
@RestController
@RequestMapping("/read-timeout-simulator")
public class ReadTimeoutSimulateController {

    private String[] apiPartsForSimulateLongResponse = {
            "<users>", //
            "  <user>", //
            "    <name>Rustam</name>", //
            "    <age>25</age>", //
            "  </user>", //
            "</users>" //
    };

    /**
     * SocketTimeoutException and ReadTimeoutException is same.
     * This service returns first 3 lines with 5 second interval and rest of lines with 10 second interval.
     * - If we set readTimeout less than 5 second, it will throw ReadTimeoutException or SocketTimeoutException.
     * - If we set readTimeout between 5 and 10, it will throw ReadTimeoutException or SocketTimeoutException after return first 3 lines.
     * - Only if we set readTimeout greater than 10 second, then we will get success response.
     */
    @GetMapping()
    public void apiPartsForSimulateLongResponse(PrintWriter pr) throws Exception {
        for (int i = 0; i < apiPartsForSimulateLongResponse.length; i++) {
            if (i < 3) {
                TimeUnit.SECONDS.sleep(5);
            } else {
                TimeUnit.SECONDS.sleep(10);
            }

            pr.println(apiPartsForSimulateLongResponse[i]);
            pr.flush();
        }
    }

}
