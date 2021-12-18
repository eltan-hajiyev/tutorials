package com.github.elten400.tutorials.tomcatbrokenpipe;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TomcatBrokenPipeApplicationTests {
    @Value("${server.port}")
    String serverPort;

    @Test
    void test() throws Exception {
        try {
            clientRequest();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (ConnectException connectException) {
            System.err.println("Launch the app first, then try test.");
            throw connectException;
        }
    }


    private void clientRequest() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        client.newCall(new Request.Builder()
                        .url(String.format("http://localhost:%s/api/info", serverPort))
                        .build())
                .execute().body().string();
    }


}
