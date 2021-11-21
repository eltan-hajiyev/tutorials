package com.github.elten400.tutorials.aoptransactional.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@DynamicUpdate
@DynamicInsert
@Data
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
}
