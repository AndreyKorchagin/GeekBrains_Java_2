package com.korchagin.java.courses.task6.services;

import com.korchagin.java.courses.task6.entity.Users;
import com.korchagin.java.courses.task6.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers(){
        return usersRepository.getAllUsers();
    }

    public Users getUserById(Long id){
        return usersRepository.getUserById(id);
    }

    public void saveUser(Users user){
        usersRepository.saveUser(user);
    }

    public void saveAllUsers(List<Users> users){
        usersRepository.saveAllUsers(users);
    }

    public void deleteUserById(Long id){ usersRepository.deleteUserById(id); }
}
