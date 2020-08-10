package com.korchagin.java.courses.task7.controllers;

import com.korchagin.java.courses.task7.entity.Users;
import com.korchagin.java.courses.task7.services.UsersService;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@AllArgsConstructor
public class UsersController {
    private UsersService usersService;

    @GetMapping("/")
    public String showRoot(){
        return "root-page";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model){
        model.addAttribute("users", usersService.getAllUsers());
        return "users-page";
    }

    @PostMapping("/users/add")
    public String addUsers(@ModelAttribute Users user){
//        model.addAttribute()
        System.out.println(user.getUserName() + " " + user.getAge());
        usersService.saveOrUpdateUser(user);
        return "redirect:/users";
    }
}
