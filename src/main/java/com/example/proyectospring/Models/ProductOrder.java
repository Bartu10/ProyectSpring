package com.example.proyectospring.Models;

import com.example.proyectospring.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer cantidad;


    public ProductOrder(Order order, Product product, Integer cantidad) {
        this.order = order;
        this.product = product;
        this.cantidad = cantidad;
    }
}


