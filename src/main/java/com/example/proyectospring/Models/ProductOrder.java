package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference(value= "product-order")
    @ManyToOne
    @JoinColumn()
    private Order order;

    @JsonBackReference(value= "product")
    @ManyToOne
    @JoinColumn()
    private Product product;

    private Integer cantidad;

    public ProductOrder(Order order, Product product, Integer cantidad) {
        this.order = order;
        this.product = product;
        this.cantidad = cantidad;
    }
}

