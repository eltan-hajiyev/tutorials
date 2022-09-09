package com.github.elten400.tutorials.webfluxprofit.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Data
public class Student implements Serializable {
    @Id
    private Integer id;
    private Boolean status = false;
    private String name;
    private Integer count = 0;
}
