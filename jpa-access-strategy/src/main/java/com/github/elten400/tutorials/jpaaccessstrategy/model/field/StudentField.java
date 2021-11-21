package com.github.elten400.tutorials.jpaaccessstrategy.model.field;

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
@Table(name = "student")
public class StudentField implements Serializable {
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
    private List<BookField> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + ":field";
    }

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