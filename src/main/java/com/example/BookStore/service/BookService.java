package com.example.BookStore.service;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookListing getListing(){
        List<Book> bookList = bookRepository.findAll();
        return new BookListing(bookList, bookList.size());
    }

public void  addBook (Book book){
        bookRepository.save(book);
}

    public Book getById(String bookId) {
        return bookRepository.findById(bookId).get();
    }
}
