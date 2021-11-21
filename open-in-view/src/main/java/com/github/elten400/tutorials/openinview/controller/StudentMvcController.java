package com.github.elten400.tutorials.openinview.controller;

import com.github.elten400.tutorials.openinview.model.Student;
import com.github.elten400.tutorials.openinview.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mvc")
public class StudentMvcController {
    private final StudentRepository studentRepository;

    @GetMapping("/students/{id}")
    @Transactional
    public ModelAndView getStudents(@PathVariable Integer id, ModelAndView model) throws Exception {
        //Thread.sleep(20000);
        Student student = studentRepository.findById(id).get();

        model.setViewName("students");
        model.addObject("student", student);

        return model;
    }
}
