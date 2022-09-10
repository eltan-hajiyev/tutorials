package com.github.elten400.tutorials.tomcatbrokenpipe;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TomcatBrokenPipeApplicationTests {
    @Value("${server.port}")
    String serverPort;

    @Autowired
    TestConfig testConfig;

    @Autowired
    ApplicationContext applicationContext;

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

        while (!testConfig.serverThrewException) {
            System.out.println("Wait for Tomcat exception....");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("Tomcat threw exception!");
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
