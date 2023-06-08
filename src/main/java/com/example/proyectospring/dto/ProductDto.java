package com.example.proyectospring.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProductDto implements Serializable{

    private String name;
    private String description;
    private Integer price;
    private String state;
    private String yr;
    private Boolean retro;
    private String team;
    private String img;
    private Integer XL;
    private Integer L;
    private Integer M;
    private Integer S;


}


