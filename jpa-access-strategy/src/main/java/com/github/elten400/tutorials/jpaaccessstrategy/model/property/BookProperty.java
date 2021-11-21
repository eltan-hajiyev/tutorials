package com.github.elten400.tutorials.jpaaccessstrategy.model.property;

import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Entity
@Table(name = "book")
public class BookProperty implements Serializable {
    private Integer id;
    private String name;
    private StudentProperty student;

    @Id
    public Integer getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public StudentProperty getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "BookProperty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}