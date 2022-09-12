package com.github.elten400.tutorials.httptimeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Eltan Hajiyev
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpClientWithNativeJava {

    private String url;

    public HttpClientWithNativeJava(@LocalServerPort int port) throws Exception {
        url = "http://localhost:" + port + "/read-timeout-simulator";
    }

    /**
     * This will throw an exception on the first read attempt.
     * Process time will be around 2 second.
     */
    @Test
    void read_timeout_less_than_5_second() throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.setReadTimeout((int) TimeUnit.SECONDS.toMillis(2));

        AtomicInteger counter = new AtomicInteger(0);

        /**
         * Proves that the exception occurs on the first line.
         */
        assertThrows(SocketTimeoutException.class, () -> {
            InputStream io = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(io));
            String rl = br.readLine();
            counter.incrementAndGet();
        });

        /**
         * Response was not received. Nothing received.
         */
        assertThat(counter.get()).isEqualTo(0);
    }

    /**
     * This will throw an exception after few successful read attempt.
     * Process time will be more than 7 second.
     */
    @Test
    void read_timeout_between_5_and_10_second() throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.setReadTimeout((int) TimeUnit.SECONDS.toMillis(7));
        InputStream io = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(io));

        AtomicInteger counter = new AtomicInteger(0);

        /**
         * Proves that the exception occurs after first line.
         */
        assertThrows(SocketTimeoutException.class, () -> {
            String rl;
            while ((rl = br.readLine()) != null) {
                System.out.println(rl);
                counter.incrementAndGet();
            }
        });

        /**
         * Only part of the response was received.
         */
        assertThat(counter.get()).isGreaterThan(2).isLessThan(6);
    }


    /**
     * It will return success response.
     * Process time will be more than 12 second.
     */
    @Test
    void read_timeout_greater_than_10_second() throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.setReadTimeout((int) TimeUnit.SECONDS.toMillis(12));
        InputStream io = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(io));

        String rl;
        while ((rl = br.readLine()) != null) {
            System.out.println(rl);
        }

        /**
         * Response received completely.
         */
    }
}
