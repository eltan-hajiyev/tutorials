package com.github.elten400.tutorials.firstlevelcache.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Book implements Serializable {
    @Id
    private Integer id;

    @Column
    private String name;

    @Override
    public String toString() {
        return "BookField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}