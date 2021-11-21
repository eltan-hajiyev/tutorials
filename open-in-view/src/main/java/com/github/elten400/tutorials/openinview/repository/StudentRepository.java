package com.github.elten400.tutorials.openinview.repository;

import com.github.elten400.tutorials.openinview.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
