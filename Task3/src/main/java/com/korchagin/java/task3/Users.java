package com.korchagin.java.task3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "user")
    List<Lots> lots;

    public Users(){}

    public Users(String userName) {
        this.userName = userName;
    }

    public String getUserName() { return userName; }
    public Long getId() { return id; }

    public void setUserName(String userName) { this.userName = userName; }

}
