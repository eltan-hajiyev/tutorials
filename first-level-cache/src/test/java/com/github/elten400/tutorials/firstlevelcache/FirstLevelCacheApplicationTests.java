package com.github.elten400.tutorials.firstlevelcache;

import com.github.elten400.tools.ThreadExecutorInfo;
import com.github.elten400.tools.ThreadPoolExecutorForInfo;
import com.github.elten400.tutorials.firstlevelcache.model.Student;
import com.github.elten400.tutorials.firstlevelcache.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.hikari.maximum-pool-size=2"
})
class FirstLevelCacheApplicationTests {
    @Autowired
    private StudentService studentService;
    private Integer studentId = 1;
    private int taskCount = 20;
    private ThreadPoolExecutorForInfo threadPoolExecutorForInfo = new ThreadPoolExecutorForInfo(20);

    /**
     * Transactional method will cache repeated call of same row.
     * None transactional will communicate with the database every time.
     */
    @Test
    public void transactional() throws Exception {
        environmentInit();

        ThreadExecutorInfo transactional = threadPoolExecutorForInfo.execute(taskCount, () -> {
            Student student = studentService.getStudentTransactional(studentId);
            return student;
        });
        ThreadExecutorInfo noneTransactional = threadPoolExecutorForInfo.execute(taskCount, () -> {
            Student student = studentService.getStudentDefault(studentId);
            return student;
        });


        System.out.println("noneTransactional: " + noneTransactional);
        System.out.println("    transactional: " + transactional);

        assertThat(transactional.getMaxTime()).isLessThan(noneTransactional.getMaxTime());
        assertThat(transactional.getMinTime()).isLessThan(noneTransactional.getMinTime());
        assertThat(transactional.getTotalTime()).isLessThan(noneTransactional.getTotalTime());
    }

    private void environmentInit() {
        /**
         * Just for init environment
         */
        threadPoolExecutorForInfo.execute(taskCount, () -> {
            Student student = studentService.getStudentDefault(studentId);
            return student;
        });
        threadPoolExecutorForInfo.execute(taskCount, () -> {
            Student student = studentService.getStudentTransactional(studentId);
            return student;
        });
    }
}
