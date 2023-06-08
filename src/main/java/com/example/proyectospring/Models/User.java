package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "usercustom")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String email;
    private Boolean admin;
    private String password;

    private String imageid;
    //@JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();



    public User(String name, String username, String email, Boolean admin, String password, String imageid){
        this.name = name;
        this.username = username;
        this.email = email;
        this.admin = admin;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.imageid = imageid;

    }


    public boolean isAdmin() {
        return admin;
    }
}
