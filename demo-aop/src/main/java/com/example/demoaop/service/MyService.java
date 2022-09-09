package com.example.demoaop.service;

import com.example.demoaop.annotation.MyAnnotationTypeMethod;
import com.example.demoaop.model.User;
import org.springframework.boot.context.properties.bind.Name;

public interface MyService {
    @MyAnnotationTypeMethod

    void method(@Deprecated User user, @Name("hello") String arg);

    void methodCached();
}
