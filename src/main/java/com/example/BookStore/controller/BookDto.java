package com.example.BookStore.controller;


import com.example.BookStore.model.Book;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true )
public class BookDto {

    private final String id;
    private final String title;
    private final String author;

    @JsonCreator
    public BookDto(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty ("authorName") String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public static BookDto fromDomain(Book book) {
        return  new BookDto(book.getId(), book.getTitle(), book.getAuthor());
    }



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Book toDomain() {
        return new Book(title, author);
    }
}
