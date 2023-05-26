package com.example.proyectospring.dto;

import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties("order")
public class OrderDto implements Serializable {
    private String fecha;
    private Long price;






}
