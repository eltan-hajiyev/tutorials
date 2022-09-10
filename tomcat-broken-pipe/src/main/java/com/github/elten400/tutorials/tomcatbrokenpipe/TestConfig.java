package com.github.elten400.tutorials.tomcatbrokenpipe;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestConfig {
    public Boolean serverThrewException = false;

    @Pointcut("execution(* javax.servlet.http.HttpServlet.service(javax.servlet.ServletRequest, javax.servlet.ServletResponse))")
    public void pointCut() {

    }

    @AfterThrowing("pointCut()")
    public void exception() {
        serverThrewException = true;
    }
}
