package com.github.elten400.tutorials.webfluxprofit;

public class ConsolePrint {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printClientError(String msg) {
        String s = Thread.currentThread().getName() + ":"+Thread.currentThread().getId() + ":";
        System.err.println(s + "client throws an error: " + msg);
    }

    public static void  printClient(String msg) {
        String s = Thread.currentThread().getName() + ":"+Thread.currentThread().getId() + ":";
        System.out.println(s + ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void  printServer(String msg) {
        String s = Thread.currentThread().getName() + ":"+Thread.currentThread().getId() + ":";
        System.out.println(s + ANSI_BLUE + msg + ANSI_RESET);
    }
}
