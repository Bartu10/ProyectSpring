package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Table(name = "products")
@Entity
@Getter @Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String state;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productsOrder = new HashSet<>();

    public Product(String name, String description, Integer price, String state) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
    }
}


