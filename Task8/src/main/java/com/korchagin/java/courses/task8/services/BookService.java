package com.korchagin.java.courses.task8.services;

import com.korchagin.java.courses.task8.entityes.Book;
import com.korchagin.java.courses.task8.exceptions.ResourceNotFoundException;
import com.korchagin.java.courses.task8.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Page<Book> findAll(Specification<Book> spec, int page, int size){
        return bookRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
    }

    public Book saveOrUpdate(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
       bookRepository.deleteById(id);
    }

    public List<Integer> findAllPublishYear(){
        return bookRepository.findAllPublishYear();
    }

}
