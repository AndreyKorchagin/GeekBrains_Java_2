package com.korchagin.java.courses.task7.services;

import com.korchagin.java.courses.task7.entity.Users;
import com.korchagin.java.courses.task7.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;

    public List<Users> getUsers(){ return usersRepository.findAll(); }

    public List<Users> getUsersWithFilter(Integer maxAge){
        List<Users> users = usersRepository.findAll();
        if (maxAge == null){
            return users;
        }
        users.removeIf(u -> u.getAge() > maxAge);
        return users;
    }

    public Users getUserById(Long id){
        return usersRepository.findById(id).orElseThrow();
    }

    public Users saveOrUpdateUser(Users user){ return usersRepository.save(user); }

    public void saveAllUsers(List<Users> users){
        usersRepository.saveAll(users);
    }

    public void deleteUserById(Long id){
        usersRepository.deleteById(id);
    }
}
