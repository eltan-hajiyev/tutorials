package com.github.elten400.tutorials.webfluxprofit.dto;

public class KeyPair {
    private String value;
    private String name;

    public KeyPair() {
    }

    public KeyPair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static KeyPair of(String name, String value) {
        return new KeyPair(name, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DTP{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}