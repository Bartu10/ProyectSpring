package com.example.proyectospring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "products")
    @Entity
    @Getter
    @Setter
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;

        private String description;

        private Integer price;


        private String state;


    @OneToMany(mappedBy = "product")
    private List<ProductOrder> products;


    }


