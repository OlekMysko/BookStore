package com.example.BookStore;

import com.example.BookStore.model.Book;
import com.example.BookStore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class BookStoreApplicationTests {
@Autowired
private BookRepository bookRepository;

@Before
public void setup(){bookRepository.deleteAll();
    Book book1 = new Book("1","Java","Author 1");
    Book book2 = new Book("2","Java","Author 2");
    Book book3 = new Book("3","Java","Author 3");

    bookRepository.save(book1);
    bookRepository.save(book2);
    bookRepository.save(book3);
}

@After
public void cleanup(){bookRepository.deleteAll();}
	@Test
	public void contextLoads() {
		//given
		//BookRepository bookRepository = new BookRepositoryImpl();

		//when
		List<Book> books = bookRepository.findAll();
		//then
        Assertions.assertThat(books.size()).isEqualTo(3);
        Assertions.assertThat(books).isEqualTo(Lists.newArrayList(
                new Book ("1","Java","Author 1"),
                new Book ("2","Java","Author 2"),
                new Book ("3","Java","Author 3")));

	}

}
