package com.github.elten400.tutorials.twoopensessions;

import com.github.elten400.tutorials.twoopensessions.model.Book;
import com.github.elten400.tutorials.twoopensessions.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * not about multi thread
 * not about spring.jpa.properties.hibernate.enable_lazy_load_no_trans
 *
 * it's about reference field Student
 */

@SpringBootTest
class TwoOpenSessionsApplicationTests {
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    BookRepository bookRepository;

    @Test
        /*-
         * Hibernate: illegally attempted to associate proxy
         */
    void hibernate_two_open_session_exception() {
        Integer bookId = 1;
        final Book book = getBook(bookId);

        Exception ex = assertThrows(org.hibernate.HibernateException.class, () -> {
            Session session = sessionFactory.openSession();

            //merge(book) method not throws Exception
            session.update(book);
        });

        assertThat(ex.getMessage()).contains("two open");

        System.err.println(ex.getMessage());
    }


    @Test
        /*-
         * Hibernate: illegally attempted to associate proxy
         */
    void repository_will_not_throw_exception1() {
        Integer bookId = 1;
        Book book = getBook(bookId);

        bookRepository.save(book);
    }

    @Test
        /*-
         * Hibernate: illegally attempted to associate proxy
         */
    void repository_will_not_throw_exception2() {
        Integer bookId = 1;
        Book book = bookRepository.findById(bookId).get();

        Session session = sessionFactory.openSession();
        session.update(book);
    }

    public Book getBook(Integer id) {
        Session session = sessionFactory.openSession();

        Book book = session.find(Book.class, id);

        //to avoid 'two open Sessions' exception
        //do one of next
        //  book.getStudent().getName(); load proxy field by calling field
        //  session.close(); close session
        //  create new student and set

        return book;
    }


}
