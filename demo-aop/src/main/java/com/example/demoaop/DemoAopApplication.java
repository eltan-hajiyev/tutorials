package com.example.demoaop;

import com.example.demoaop.model.User;
import com.example.demoaop.service.MyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DemoAopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoAopApplication.class, args);
    }


    @Autowired
    MyServiceImpl myService;

    @Override
    public void run(String... args) throws Exception {
        myService.method(new User(), "asd");
//        myService.hello();
    }
}
