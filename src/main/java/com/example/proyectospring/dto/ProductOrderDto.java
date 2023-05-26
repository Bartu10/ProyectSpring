package com.example.proyectospring.dto;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ProductOrderDto implements Serializable {

    private Integer cantidad;


}
