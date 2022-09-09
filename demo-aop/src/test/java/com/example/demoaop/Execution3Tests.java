package com.example.demoaop;

import com.example.demoaop.model.User;
import com.example.demoaop.service.MyService;
import com.example.demoaop.service.MyServiceImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@SpringBootTest(classes = {MyServiceImpl.class, Execution3Tests.AspectConfig.class})
class Execution3Tests {

    @Autowired
    MyService myService;

    @Autowired
    AspectConfig aspect;

    @Test
    void contextLoads() {
        myService.method(new User(), "arg");

        assertThat(aspect.b).isTrue();
    }

    @Aspect
    @Component
    public static class AspectConfig {
        boolean b = false;

        @Pointcut("execution(* com.example.demoaop.service.MyServiceImpl..*(..))")
        public void pointCut() {
        }

        @After("pointCut()")
        public void advice() {
            b = true;
            System.out.println("execution: .* or ..* all methods");
            System.out.println("execution: .. for 0 or + arguments");
            System.out.println("execution: * for single argument");
        }
    }


}
