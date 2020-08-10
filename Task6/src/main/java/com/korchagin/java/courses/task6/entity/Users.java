package com.korchagin.java.courses.task6.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "age")
    private Integer age;

    public Users(){}

    public Users(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public void print(){
        System.out.println(id + " " + userName + " " + age);
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public Integer getAge() { return age; }

    public void setId(Long id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setAge(Integer age) { this.age = age; }
}
