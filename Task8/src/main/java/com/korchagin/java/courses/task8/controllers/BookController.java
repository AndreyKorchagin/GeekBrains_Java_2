package com.korchagin.java.courses.task8.controllers;

import com.korchagin.java.courses.task8.entityes.Book;
import com.korchagin.java.courses.task8.entityes.Genre;
import com.korchagin.java.courses.task8.services.BookService;
import com.korchagin.java.courses.task8.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        model.addAttribute("currentYear", params.get("year") != null ? params.get("year") : 0);
        model.addAttribute("genre", Genre.values());
        return "store-page";
    }

    @PostMapping
    public String showAllBooksWithPublishYear(@Validated String years, @Validated String genre){
        StringBuilder stringBuilder = new StringBuilder();
        List<String> listGenre = new ArrayList<>();

        if (!years.equals("zero")){
            stringBuilder.append("redirect:/books?year=").append(years);
            if (genre != null){
                listGenre = Arrays.asList(genre.split(","));
                stringBuilder.append("&genre=");
                if (listGenre.size() >= 2){
                    for (int i = 1; i < listGenre.size(); i++) {
                        stringBuilder.append(listGenre.get(i)).append("-");
                    }
                    stringBuilder.setLength(stringBuilder.length() - 1);
                } else {
                    stringBuilder.append(listGenre.get(0));
                }
            }
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        }
        if (genre != null){
            listGenre = Arrays.asList(genre.split(","));
            stringBuilder.append("redirect:/books?genre=");
            if (listGenre.size() >= 2){
                for (int i = 1; i < listGenre.size(); i++) {
                    stringBuilder.append(listGenre.get(i)).append("-");
                }
                stringBuilder.setLength(stringBuilder.length() - 1);
            } else {
                stringBuilder.append(listGenre.get(0));
                System.out.println(listGenre.get(0));
            }
            System.out.println("22 = " + stringBuilder);
            return stringBuilder.toString();
        }
        return "redirect:/books";
    }

    @GetMapping("/rest")
    @ResponseBody
    @CrossOrigin("*")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
