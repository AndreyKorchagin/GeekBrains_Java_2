package com.geekbrains.book.store.controllers.rest;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.entities.dto.BookDto;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    private BookService bookService;

//    @GetMapping
//    public Page<Book> getAllBooks(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                  @RequestParam(required = false) MultiValueMap<String, String> params) {
//        BookFilter bookFilter = new BookFilter(params);
//        Page<Book> pageBook = bookService.findAllBooks(bookFilter.getSpec(), page - 1, 5);
//        return pageBook;
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(required = false) MultiValueMap<String, String> params) {
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> pageBook = bookService.findAll(bookFilter.getSpec(), page - 1, 5);

        List<List<String>> genres = new ArrayList<>();
        for (Genre g: Genre.values()) {
            List<String> temp = new ArrayList<>();
            temp.add(g.toString());
            temp.add(g.getGenreName());
            genres.add(temp);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("book", pageBook);
//        response.put("genres", Genre.values());
        response.put("genres", genres);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewBook(@RequestBody Book book) {
        if (book.getId() != null) {
            book.setId(null);
        }
        return bookService.saveOrUpdate(book);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Book updateBook(@RequestBody Book book) {
        if (!bookService.existsById(book.getId())) {
            throw new ResourceNotFoundException(String.format("Книга с id= %d не найдена", book.getId()));
        }
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllBooks() {
        bookService.deleteAll();
    }

}