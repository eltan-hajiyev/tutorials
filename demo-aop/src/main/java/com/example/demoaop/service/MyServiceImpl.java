package com.example.demoaop.service;

import com.example.demoaop.annotation.MyAnnotationTypeClassCompile;
import com.example.demoaop.annotation.MyAnnotationTypeClassRuntime;
import com.example.demoaop.annotation.MyAnnotationTypeMethod;
import com.example.demoaop.model.User;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@MyAnnotationTypeClassCompile
@MyAnnotationTypeClassRuntime
public class MyServiceImpl implements MyService {

    @Override
    @MyAnnotationTypeMethod
    public void method(@Deprecated User user, @Name("hello") String arg) {
        System.out.println("Hello world!");
    }

    @Cacheable("test")
    public void methodCached() {
        System.out.println("Hello world!");
    }

}
