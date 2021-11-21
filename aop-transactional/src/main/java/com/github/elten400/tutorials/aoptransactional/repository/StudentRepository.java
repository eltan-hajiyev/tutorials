package com.github.elten400.tutorials.aoptransactional.repository;

import com.github.elten400.tutorials.aoptransactional.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
