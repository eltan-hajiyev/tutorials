package com.github.elten400.tutorials.openinview.controller;

import com.github.elten400.tutorials.openinview.model.Student;
import com.github.elten400.tutorials.openinview.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest")
public class StudentRestController {
    private final StudentRepository studentRepository;

    @GetMapping("/students/{id}")
    @Transactional
    public Student getStudents(@PathVariable Integer id) throws Exception {
        //Thread.sleep(20000);
        Student student = studentRepository.findById(id).get();

        return student;
    }
}
