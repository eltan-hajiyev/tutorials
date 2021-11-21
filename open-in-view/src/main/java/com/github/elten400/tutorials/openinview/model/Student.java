package com.github.elten400.tutorials.openinview.model;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<Book> books;

    @Override
    public String toString() {
        return "StudentField{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}