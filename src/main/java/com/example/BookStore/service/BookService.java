package com.example.BookStore.service;

import com.example.BookStore.exception.BookNotFoundException;
import com.example.BookStore.exception.InvalidSearchFiltersException;
import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.model.SearchFilters;
import com.example.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookListing getListing() {
        List<Book> bookList = bookRepository.findAll();
        return new BookListing(bookList, bookList.size());
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book getById(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public void deleteById(String bookId) {
        Optional<Book> bookToRemove = bookRepository.findById(bookId);
        bookToRemove.ifPresent(bookRepository::delete);
    }

    public List<Book> getByAuthorStartsWith(String autor) {
        return bookRepository.findByAuthorStartsWith(autor);
    }

    public List<Book> getByTitleStartsWith(String autor) {
        return bookRepository.findByTitleStartsWith(autor);
    }

    public BookListing getFilteredListing(SearchFilters searchFilters) {
        List<Book> bookList;
        if (searchFilters.hasAuthor() && searchFilters.hasTitle()) {
            //wyjątek
            throw new InvalidSearchFiltersException();
        }
            if (searchFilters.hasAuthor()) {
                bookList = bookRepository.findByAuthorStartsWith(searchFilters.getAuthor());
            } else if (searchFilters.hasTitle()) {
                bookList = bookRepository.findByTitleStartsWith(searchFilters.getTitle());
            } else {
                bookList = bookRepository.findAll();
            }
        return new BookListing(bookList, bookList.size());
    }


}