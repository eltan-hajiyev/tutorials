package com.github.elten400.tutorials.twoopensessions.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Boolean status = false;

    @Column
    private String name;

    @Column
    private Integer count = 0;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Book> books;
}