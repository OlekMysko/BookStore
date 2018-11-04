package com.example.BookStore.model;

public class SearchFilters {
    private final  String title;
    private final  String author;

    public SearchFilters(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public boolean hasTitle(){
        return title !=null;
    }
    public boolean hasAuthor(){
        return author !=null;
    }

}
