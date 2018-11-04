package com.example.BookStore.repository;


import com.example.BookStore.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class BookRepositoryImpl implements BookRepository {
    private static List<Book> books = new ArrayList<>();
    private static final AtomicInteger currentId = new AtomicInteger(0);


    @Override
    public Book save(Book book) {
        Book prepareBook = prepare(book);
        books.add(prepare(prepareBook));
        return prepareBook;
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
                        book.getTitle(),
                        book.getAuthor()));

    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> findByAuthorStartsWith(String author) {
        return books.stream()
                .filter(book -> book
                        .getAuthor()
                        .toLowerCase()
                        .startsWith(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTitleStartsWith(String title) {
        return books.stream()
                .filter(book -> book
                        .getTitle()
                        .toLowerCase()
                        .startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        books.clear();
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    public void deleteById(String id) {
        findById(id).ifPresent(book -> books.remove(book));
    }

    @Override
    public Optional<Book> findById(String id) {
        return books.stream().filter(book -> id.equals(book.getId())).findFirst();
    }
}
