package com.example.BookStore.service;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookListing;
import com.example.BookStore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnListing() throws  Exception{
        //given
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        when(bookRepository.findAll()).thenReturn(
                Lists.newArrayList(
                        new Book("1","title1","author1"),
                        new Book("2","title2","author2")
                )
        );
BookService bookService = new BookService(bookRepository);
        //when
        BookListing bookListing = bookService.getListing();
        //than
        Assertions.assertThat(bookListing.getNumberOfBooks()).isEqualTo(2);


    }
}