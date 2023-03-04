package com.example.proyectospring.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity @Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private String fecha;
    private Long price;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> orders;

    public Order() {}

    public Order(String fecha, Long price, User user){
        this.fecha = fecha;
        this.price = price;
        this.user = user;
    }


}


