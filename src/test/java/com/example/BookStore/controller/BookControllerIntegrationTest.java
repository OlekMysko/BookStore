package com.example.BookStore.controller;

import com.example.BookStore.model.Book;
import com.example.BookStore.model.BookDto;
import com.example.BookStore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {
        bookRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAll();
    }

    @Test
    public void shouldReturnExistingBook() throws Exception {
        //given
        Book first = bookRepository.save(new Book("title1", "author1"));
        Book second = bookRepository.save(new Book("title2", "author2"));

        HttpHeaders headers =new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_UTF8_VALUE);
        //when
        ResponseEntity<BookDto> book = testRestTemplate.exchange(
          prepareURL("books/"+second.getId()),
          HttpMethod.GET,
          new HttpEntity<>(headers),
          BookDto.class
        );
//then
        Assertions.assertThat(book.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(book.getBody()).isEqualTo(new BookDto(second.getId(),"title2","author2"));
    }

    @Test
    public void shouldReturnFakeTest(){
        //when
        ResponseEntity<String> result = testRestTemplate.getForEntity(prepareURL("/books/fake"),String.class);
        //then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isEqualTo("some text");

    }



    private String prepareURL(String url){
        return "http://localhost:"+ port + "/"+url;
    }
}