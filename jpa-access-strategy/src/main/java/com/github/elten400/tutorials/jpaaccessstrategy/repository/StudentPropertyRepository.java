package com.github.elten400.tutorials.jpaaccessstrategy.repository;

import com.github.elten400.tutorials.jpaaccessstrategy.model.property.StudentProperty;
import org.springframework.data.repository.CrudRepository;

public interface StudentPropertyRepository extends CrudRepository<StudentProperty, Integer> {
}
