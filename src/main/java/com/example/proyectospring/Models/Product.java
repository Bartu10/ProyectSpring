package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "products")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
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

    @JsonBackReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders = new ArrayList<>();

    public Product(String name, String description, Integer price, String state, String yr, Boolean retro, String team,String img, Integer XL, Integer L, Integer M, Integer S) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
        this.yr = yr;
        this.retro = retro;
        this.team = team;
        this.img = img;
        this.XL = XL;
        this.L = L;
        this.M = M;
        this.S = S;


    }
}


