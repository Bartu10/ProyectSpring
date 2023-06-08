package com.example.proyectospring.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<ProductOrder> productOrders = new HashSet<>();



    public Order(String fecha, Long price, User user){
        this.fecha = fecha;
        this.price = price;
        this.user = user;
    }


}


