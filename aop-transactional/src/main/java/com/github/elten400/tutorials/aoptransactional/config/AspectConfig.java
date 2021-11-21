package com.github.elten400.tutorials.aoptransactional.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Aspect
@Component
public class AspectConfig {
    public static final int AOP_IMPACT_SECOND = 3;

    @Pointcut("@within(org.springframework.stereotype.Service)")
    protected void serviceMethods() { }

    @AfterReturning("serviceMethods()")
    public void aroundServiceMethods() throws Throwable {
        System.out.println("started procedure inside AOP method");
        TimeUnit.SECONDS.sleep(AOP_IMPACT_SECOND);
        System.out.println("finished procedure inside AOP method");
    }
}
