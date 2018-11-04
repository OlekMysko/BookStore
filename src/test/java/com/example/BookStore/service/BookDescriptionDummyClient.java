package com.example.BookStore.service;

import static org.junit.Assert.*;

public class BookDescriptionDummyClient implements BookDescriptionClient {
    @Override
    public String getDescription(String bookId){
        return "super książka";
    }

}