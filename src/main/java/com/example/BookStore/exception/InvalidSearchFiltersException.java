package com.example.BookStore.exception;

public class InvalidSearchFiltersException extends RuntimeException {
    public InvalidSearchFiltersException(){
        super("Invalid serch filters exception. Choose author OR title");
    }
}
