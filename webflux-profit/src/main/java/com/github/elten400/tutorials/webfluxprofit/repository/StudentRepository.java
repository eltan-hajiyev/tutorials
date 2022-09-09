package com.github.elten400.tutorials.webfluxprofit.repository;

import com.github.elten400.tutorials.webfluxprofit.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer>
{

}
