package com.korchagin.java.courses.task8.controllers;

import com.korchagin.java.courses.task8.entityes.Book;
import com.korchagin.java.courses.task8.services.BookService;
import com.korchagin.java.courses.task8.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                               @RequestParam Map<String, String> params
                               ){
        int size = 5;
        BookFilter bookFilter = new BookFilter(params);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, size);
        model.addAttribute("books", page.getContent());
        model.addAttribute("pageCount", page.getTotalPages());
        model.addAttribute("currentPage", pageIndex);
        model.addAttribute("years", bookService.findAllPublishYear());
        System.out.println(page.getTotalPages());
        return "store-page";
    }

    @PostMapping
    public void showAllBooks(@ModelAttribute("years") Integer year, Model model){
        System.out.println("RTER = " + year);
//        return "redirect:/store-page";
    }

    @GetMapping("/rest")
    @ResponseBody
    @CrossOrigin("*")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
