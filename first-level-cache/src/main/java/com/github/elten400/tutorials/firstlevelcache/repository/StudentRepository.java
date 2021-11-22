package com.github.elten400.tutorials.firstlevelcache.repository;

import com.github.elten400.tutorials.firstlevelcache.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
