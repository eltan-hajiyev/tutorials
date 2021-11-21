package com.github.elten400.tutorials.jpaaccessstrategy;

import com.github.elten400.tutorials.jpaaccessstrategy.model.property.BookProperty;
import com.github.elten400.tutorials.jpaaccessstrategy.model.property.StudentProperty;
import com.github.elten400.tutorials.jpaaccessstrategy.repository.StudentPropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
})
class JpaAccessStrategyApplicationNoTransactionalTests {


    @Autowired
    private StudentPropertyRepository studentPropertyRepository;

    /**
     * To make LazyLoading work without transactional you should use '...enable_lazy_load_no_trans=true' property.
     */
    @Test
    void LazyLoading_with_property_enable_lazy_load_no_trans() {
        StudentProperty student = studentPropertyRepository.findById(1).get();
        System.err.println(student.getId() + ":" + student.getName());
        BookProperty book = student.getBooks().get(0);
    }
}
