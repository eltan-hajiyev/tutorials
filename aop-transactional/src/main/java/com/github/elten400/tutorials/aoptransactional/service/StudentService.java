package com.github.elten400.tutorials.aoptransactional.service;

import com.github.elten400.tutorials.aoptransactional.model.Student;
import com.github.elten400.tutorials.aoptransactional.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudentDefault(Integer id) {
        return getStudent(id);
    }

    @Transactional
    public Student getStudentTransactional(Integer id) {
        return getStudent(id);
    }

    public Student getStudent(Integer id) {
        Student student = studentRepository.findById(id).get();
        System.err.println(":::::::" + student);

        return student;
    }

}
