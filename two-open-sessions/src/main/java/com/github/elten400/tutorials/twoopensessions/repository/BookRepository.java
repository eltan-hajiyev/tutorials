package com.github.elten400.tutorials.twoopensessions.repository;

import com.github.elten400.tutorials.twoopensessions.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
