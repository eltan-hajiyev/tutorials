package com.example.demoaop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@EnableAspectJAutoProxy
@SpringBootTest(classes = {
        DifferenceThisAndTarget1Tests.MyService.class,
        DifferenceThisAndTarget1Tests.AspectConfig.class,
        ConcurrentMapCacheManager.class
})
class DifferenceThisAndTarget1Tests {
    @Autowired
    MyService myService;

    @Test
    void test() {
        myService.methodCached();
        myService.method("1");
    }

    @Service
    public static class MyService {
        public void method(String arg) {
            System.out.println("method called");
        }

        @Cacheable("test")
        public void methodCached() {
            System.out.println("methodCached called");
        }
    }

    @Component
    @Aspect
    @EnableCaching
    public static class AspectConfig {
        @Pointcut("execution(* com.example.demoaop.DifferenceThisAndTarget1Tests.MyService.method(*))")
        public void pointCut() {
        }

        /**
         * Just update target to this and compare result
         * 1. With designator 'target' caching will not work.
         *    Because, it will return object without proxy.
         * 2. With designator 'this' caching will work.
         *    Because, it will return proxy object which includes caching logic.
         */
        @After("pointCut() && target(service)")
        public void advice(MyService service) {
            System.out.println("Advice worked!");
            service.methodCached();
        }
    }
}
