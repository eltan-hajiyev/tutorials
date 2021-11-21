package com.github.elten400.tutorials.jpaaccessstrategy.model.field;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "book")
public class BookField implements Serializable {
    @Id
    private Integer id;

    @Column
    private String name;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StudentField student;

    @Override
    public String toString() {
        return "BookField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}