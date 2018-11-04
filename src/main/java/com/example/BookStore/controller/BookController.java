package com.example.BookStore.controller;

import com.example.BookStore.model.BookListing;
import com.example.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
@Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping (method = RequestMethod.GET, value = "/fake")
    public String getSomeText(){
    return "some text";
}
@GetMapping
public BookListing getListing(){
    return bookService.getListing();
}
@GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public BookDto getBook(@PathVariable String bookId){
    return BookDto.fromDomain(bookService.getById(bookId));
}




@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ResponseStatus(HttpStatus.CREATED)
public void addBook(@RequestBody BookDto bookDto){
    bookService.addBook(bookDto.toDomain());
}

}
