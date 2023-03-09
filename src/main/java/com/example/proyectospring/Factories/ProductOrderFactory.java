package com.example.proyectospring.Factories;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Models.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
@Component
public class ProductOrderFactory {


    Faker esFaker = new Faker(new Locale("es-ES"));
    Random rand = new Random();
    public List<ProductOrder> get(int number, Order orders, Product products) {
        List<ProductOrder> productOrders = new ArrayList<>();
        for (int i = 0; i < number; i++)
            productOrders.add(new ProductOrder(
            orders, products,esFaker.number().numberBetween(1,30))
            );
        return productOrders;

    }
}