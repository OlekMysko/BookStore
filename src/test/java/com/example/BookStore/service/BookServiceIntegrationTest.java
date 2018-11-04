package com.example.BookStore.service;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceIntegrationTest {

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
    }


    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Test
    public void getListing() throws Exception {
        //given
bookRepository.deleteAll();
bookRepository.save(new Book("1","title 1", "author1"));
bookRepository.save(new Book("2","title 2", "author2"));
bookRepository.save(new Book("3","title 3", "author3"));
        //when
        BookListing bookListing = bookService.getListing();
        //then
        Assertions.assertThat(bookListing.getNumberOfBooks()).isEqualTo(3);
        Assertions.assertThat(bookListing.getBooks()).isEqualTo(
                Lists.newArrayList(
                new Book("1","title1", "author1"),
                new Book("2","title2", "author2"),
                new Book("3","title3", "author3")
        )
        );
    }
}