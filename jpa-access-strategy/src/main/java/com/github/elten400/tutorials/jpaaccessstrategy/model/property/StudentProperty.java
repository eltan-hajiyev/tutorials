package com.github.elten400.tutorials.jpaaccessstrategy.model.property;

import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "student")
public class StudentProperty implements Serializable {
    private Integer id;
    private Boolean status = false;
    private String name;
    private Integer count = 0;
    private List<BookProperty> books;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + ":property";
    }

    @Column
    public Boolean getStatus() {
        return status;
    }

    @Column
    public Integer getCount() {
        return count;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    public List<BookProperty> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "StudentProperty{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}