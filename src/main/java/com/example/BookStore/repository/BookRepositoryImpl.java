package com.example.BookStore.repository;


import com.example.BookStore.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BookRepositoryImpl implements BookRepository {
    private static List<Book> books = new ArrayList<>();
    private static final AtomicInteger currentId = new AtomicInteger(0);


    @Override
    public Book save(Book book) {
        books.add(prepare(book));
        return book;
    }

    private Book prepare(Book book) {
    /*    if (book.getId() != null) {
            return book;
        } else {
            return new Book(
                    String.valueOf(currentId.incrementAndGet()),
                    book.getAuthor(),
                    book.getAuthor());}
   */
    return Optional.ofNullable(book.getId())
            .map(id -> book)
            .orElse(new Book(
                    String.valueOf(currentId.incrementAndGet()),
                    book.getAuthor(),
                    book.getTitle()));

    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void deleteAll() {
        books.clear();
    }

    @Override
    public Optional<Book> findById(String id) {
        return books.stream().filter(book -> id.equals(book.getId())).findFirst();
    }
}
