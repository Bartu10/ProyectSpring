package com.example.proyectospring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "usercustom")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String username;
    private String email;
    private String password;


    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    public User(){}

    public User(String name, String username, String email, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }




}
