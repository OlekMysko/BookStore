package com.example.BookStore;

import com.example.BookStore.model.Book;
import com.example.BookStore.repository.BookRepository;
import com.example.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Optional;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {
    @Autowired
private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStoreApplication that = (BookStoreApplication) o;
        return Objects.equals(bookRepository, that.bookRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookRepository);
    }

    @Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring");

		Book book1 =new Book("Java","Autor 1");
		Book book2 =new Book("Java","Autor 2");
		Book book3 =new Book("Java","Autor 3");

       // BookRepository bookRepository = new BookRepositoryImpl();//@autowired - autopowiązania

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        System.out.println(bookService.getListing());
        /*System.out.println(bookRepository.findAll());
        Optional<Book> bookOptional =bookRepository.findById("2");
        if (bookOptional.isPresent()){ //czy jest
            System.out.println(bookOptional.get());
        }
        bookOptional.ifPresent(book -> System.out.println(book)); jak wysytępuje to zrobi*/


	}
}
