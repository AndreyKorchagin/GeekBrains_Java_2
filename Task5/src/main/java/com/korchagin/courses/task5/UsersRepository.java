package com.korchagin.courses.task5;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersRepository {
    private final DBSession dbs;

    @Autowired
    public UsersRepository(DBSession dbs) {
        this.dbs = dbs;
    }

    public List<Users> getAllUsers(){
        Session session = dbs.getSession();
        session.getTransaction().begin();
        List<Users> users = session.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        session.getTransaction().commit();
        return users;
    }

    public Users getUserById(Long id){
        Session session = dbs.getSession();
        session.getTransaction().begin();
        Users user = session.find(Users.class, id);
        session.getTransaction().commit();
        return user;
    }

    public void saveUser(Users user){
        Session session = dbs.getSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
    }

    public void saveAllUsers (List<Users> user){
        for (Users u: user) {
            saveUser(u);
        }
    }
}
