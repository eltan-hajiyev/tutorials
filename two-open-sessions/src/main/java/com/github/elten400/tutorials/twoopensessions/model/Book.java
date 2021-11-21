package com.github.elten400.tutorials.twoopensessions.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Book implements Serializable {
    @Id
    private Integer id;

    @Column
    private String name;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}