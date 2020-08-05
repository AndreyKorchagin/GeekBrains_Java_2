package com.korchagin.courses.task5;

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
}
