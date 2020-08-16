package com.korchagin.java.courses.task7.controllers;

import com.korchagin.java.courses.task7.entity.Users;
import com.korchagin.java.courses.task7.services.UsersService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/all")
    public String showAllUsers(Model model, @RequestParam(required = false) Integer maxAge){

        model.addAttribute("users", usersService.getUsersWithFilter(maxAge));
        return "users-page";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute Users user){
        usersService.saveOrUpdateUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model){
        model.addAttribute("users", usersService.getUserById(id));
//        usersService.saveOrUpdateUser(user);
        return "users-edit-page";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute Users user){
        usersService.saveOrUpdateUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/users/remove/{id}")
    public String removeUserById(@PathVariable Long id){
        usersService.deleteUserById(id);
        return "redirect:/users/all";
    }


}
