package com.example.BookStore.controller;

import com.example.BookStore.model.Book;
import com.example.BookStore.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository.deleteAll();
    }

    @After
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void shouldReturnExistingBook() throws Exception {
        //given
        Book first = bookRepository.save(new Book("title1", "author1"));
        Book second = bookRepository.save(new Book("title2", "author2"));
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + second.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                //then
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(asJsonString(new Book(second.getId(),"title2","author2"))));
    }
    @Test
    public void shouldReturn404() throws Exception {
        //given

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/books/indexZszafy" )
                .accept(MediaType.APPLICATION_JSON_UTF8))
                //then
                .andExpect(status().isNotFound());
    }

    private String asJsonString(Book book) {
        try {
            return new ObjectMapper().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();

        }
    }



}