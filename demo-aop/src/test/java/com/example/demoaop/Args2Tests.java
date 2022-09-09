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
@SpringBootTest(classes = {MyServiceImpl.class, Args2Tests.AspectConfig.class})
class Args2Tests {

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

        @Pointcut("args(com.example.demoaop.model.User, java.lang.String)")
        public void pointCut() {
        }

        @Before("pointCut() && args(user, description, ..)") //: Advice type
        public void advice(User user, String description) {
            b = true;
            System.out.println("@args: annotation target type must be parameter: ..." + description);
            System.out.println("args: value must be class");
            System.out.println("args: use for match and catch argument. Inside advice for param instance.");
            System.out.println("args: Argument order must match");
            System.out.println("args: Use with class match");
            System.out.println("args: If you use only args pointcut it can throw exception. It's looks like a bug. Because it works sometimes.");
            System.out.println();
        }
    }

}
