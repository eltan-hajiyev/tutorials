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
@SpringBootTest(classes = {MyServiceImpl.class, Target1Tests.AspectConfig.class})
class Target1Tests {

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

        @Pointcut("target(com.example.demoaop.service.MyService)")
        public void pointCut() {
        }

        @Before("pointCut()") //: Advice type
        public void advice() {
            b = true;
            System.out.println("target: all extends of interface or class");
            System.out.println();
        }
    }

}
