package com.korchagin.java.courses.task6.controllers;

import com.korchagin.java.courses.task6.entity.Users;
import com.korchagin.java.courses.task6.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/")
    public String showRoot(){
        return "root-page";
    }

    @RequestMapping(value = "users")
    public String showAllUsers(Model model){
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "users-page";
    }

    @RequestMapping(value = "/users/add")
    public String addUsers(@RequestParam String name, @RequestParam Integer age){
        Users user = new Users(name, age);
        usersService.saveUser(user);
        return "redirect:/users";
    }

    @RequestMapping("users/find")
    @ResponseBody
    public String findUser(Model model, @RequestParam Long id){
        Users user = usersService.getUserById(id);
        if (user == null){
            return "ERROR";
        } else {
            model.addAttribute("users", user);
            return "users-find";
        }
    }

    @GetMapping("users/remove/{id}")
    public String deleteUserById(@PathVariable(value = "id") Long id){
        usersService.deleteUserById(id);
        return "redirect:/users";
    }
}
