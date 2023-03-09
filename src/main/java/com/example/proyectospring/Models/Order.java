package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@Table(name = "orders")
@Entity @Getter @Setter
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String fecha;
    private Long price;

    /*@JsonIgnoreProperties("orders")*/
    @JsonBackReference
    @ManyToOne
    @JoinColumn()
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "order")
    private Set<ProductOrder> orders = new HashSet<>();

    public Order(String fecha, Long price, User user){
        this.fecha = fecha;
        this.price = price;
        this.user = user;
    }


}


