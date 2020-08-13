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

import java.util.ArrayList;
import java.util.Arrays;
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
        model.addAttribute("currentYear", params.get("year") != null ? params.get("year") : 0);
        model.addAttribute("genre", Genre.values());
//        model.addAttribute("currentGenre", params.get("genre") != null ? params.get("genre") : null);
//        System.out.println(params.get("genre") != null ? params.get("genre") : null);

//        for (Map<String, String> m: params) {
//            System.out.println();
//        }
        System.out.println("PARAM = " + params);
        return "store-page";
    }

    @PostMapping
    public String showAllBooksWithPublishYear(@Validated String years, @Validated String genre){
        List<String> listGenre = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(genre);

        if (!years.equals("zero") && genre != null) {
            System.out.println("111111");
            listGenre = Arrays.asList(genre.split(","));
            for (String s :  listGenre) {
                stringBuilder.append(s).append("-");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);

            return new StringBuilder("redirect:/books?year=").append(years).append("&genre=").append(stringBuilder).toString();
        }
        if (years.equals("zero") && genre != null){
            System.out.println("22222");
            listGenre = Arrays.asList(genre.split(","));
            stringBuilder.append("redirect:/books?genre=");
            for (String s: listGenre) {
                stringBuilder.append(s).append("-");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            System.out.println("SB = " + stringBuilder);
//            return new StringBuilder("redirect:/books?genre=").append(listGenre.get(0)).toString();
            return stringBuilder.toString();
        }
        System.out.println("G = " + genre);
//        System.out.println(genre != null ? genre : "KO NULL");
        return "redirect:/books";
    }

    @GetMapping("/rest")
    @ResponseBody
    @CrossOrigin("*")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
