package com.example.demoaop;

import com.example.demoaop.model.User;
import com.example.demoaop.service.MyService;
import com.example.demoaop.service.MyServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@SpringBootTest(classes = {MyServiceImpl.class, Within2Tests.AspectConfig.class})
class Within2Tests {

    @Autowired
    MyService myService;

    @Autowired
    AspectConfig aspect;

    @Test
    void contextLoads() {
        myService.method(new User(), "arg");

        assertThat(aspect.b).isTrue();
    }

    @Component
    @Aspect
    public static class AspectConfig {
        boolean b = false;

        @Pointcut("within(@org.springframework.stereotype.Service *)")
        public void pcAllClassesWithAnnotation() {
        }

        @Pointcut("within(com.example.demoaop.service.MyServiceImpl)")
        public void pcAllSubclassesOfClass() {
        }

        @Before("pcAllClassesWithAnnotation() && pcAllSubclassesOfClass()") //: Advice type
        public void advice() {
            b = true;
            System.out.println("@within: annotations with target type class");
            System.out.println("within: must be class or classes group");
            System.out.println();
        }
    }

}
