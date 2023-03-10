package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String state;

    @JsonManagedReference(value= "product")
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductOrder> productsOrder = new HashSet<>();

    public Product(String name, String description, Integer price, String state) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.state = state;
    }
}


