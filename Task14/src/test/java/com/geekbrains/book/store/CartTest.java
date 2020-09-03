package com.geekbrains.book.store;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.geekbrains.book.store.entities.Book.Genre;

import java.math.BigDecimal;

@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private BookService bookService;

    private Book bookOne = new Book(1L, "HP1", "DISK1", new BigDecimal("100.0"), 2010, Genre.FANTASY);
    private Book bookTwo = new Book(2L, "HP2", "DISK2", new BigDecimal("200.0"), 1020, Genre.FICTION);

}
