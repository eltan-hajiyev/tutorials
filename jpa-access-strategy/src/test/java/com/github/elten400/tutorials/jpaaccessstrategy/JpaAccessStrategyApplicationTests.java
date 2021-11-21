package com.github.elten400.tutorials.jpaaccessstrategy;

import com.github.elten400.tutorials.jpaaccessstrategy.model.field.StudentField;
import com.github.elten400.tutorials.jpaaccessstrategy.model.property.BookProperty;
import com.github.elten400.tutorials.jpaaccessstrategy.model.property.StudentProperty;
import com.github.elten400.tutorials.jpaaccessstrategy.repository.StudentFieldRepository;
import com.github.elten400.tutorials.jpaaccessstrategy.repository.StudentPropertyRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JpaAccessStrategyApplicationTests {

    @Autowired
    private StudentFieldRepository studentFieldRepository;

    @Autowired
    private StudentPropertyRepository studentPropertyRepository;

    @Test
    void LazyLoading_by_default_will_throw_exception() {
        StudentProperty student = studentPropertyRepository.findById(1).get();

        System.err.println(student.getId() + ":" + student.getName());

        assertThrows(LazyInitializationException.class, () -> {
            BookProperty book = student.getBooks().get(0);
        });
    }

    /**
     * LazyLoading works with Transactional session
     * Both supports lazy load
     */
    @Test
    @Transactional
    void LazyLoading_will_work_with_transactional() {
        StudentProperty studentProperty = studentPropertyRepository.findById(1).get();
        StudentField studentField = studentFieldRepository.findById(1).get();

        System.err.println(studentProperty.getBooks());
        System.out.println(studentField.getBooks());
    }


    /**
     * Field base annotation uses field for assignment
     */
    @Test
    void FieldBase_JPA_annotation_uses_fields() {
        StudentField student = studentFieldRepository.findById(1).get();

        System.err.println("::" + student);
        assertThat(student.getName()).doesNotEndWith("field");
    }

    /**
     * Property base annotation uses accessors for assignment
     */
    @Test
    void PropertyBase_JPA_annotation_uses_accessors() {
        StudentProperty student = studentPropertyRepository.findById(1).get();

        System.err.println("::" + student);
        assertThat(student.getName()).endsWith("property");
    }

}
