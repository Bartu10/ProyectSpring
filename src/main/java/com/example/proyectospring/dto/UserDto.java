package com.example.proyectospring.dto;

import com.example.proyectospring.Models.Order;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserDto implements Serializable {

    private String name;
    private String username;
    private String email;
    private Boolean admin;
    private String password;


}
