package com.example.BookStore.repository;

import com.example.BookStore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save (Book book);
    List<Book> findAll();
    void deleteAll();
    Optional<Book> findById(String id);

}
