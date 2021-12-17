package com.github.elten400.tutorials.log4jjndibug.simulate;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class LdapSocketSimulator extends Thread {
    private static ServerSocket ldapSocket;
    private Long timeOut;

    public LdapSocketSimulator(Integer port, Long timeOut) throws Exception {
        this.timeOut = timeOut;
        ldapSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("ldap simulator ready for new request!");
                Socket socket = ldapSocket.accept();
                socketClose(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void socketClose(Socket socket) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(timeOut);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
