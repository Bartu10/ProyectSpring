package com.example.proyectospring.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProductOrder {
    @Id
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private Number Int;

    public ProductOrder(){}

    public ProductOrder(Order order, Product product){
        this.order = order;
        this.product = product;
    }
}

