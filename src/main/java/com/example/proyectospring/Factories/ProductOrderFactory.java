package com.example.proyectospring.Factories;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Models.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Component
public class ProductOrderFactory {


    Faker esFaker = new Faker(new Locale("es-ES"));

    public List<ProductOrder> get(int number) {
        List<ProductOrder> productOrders = new ArrayList<>();
        for (int i = 0; i < number; i++)
            productOrders.add(new ProductOrder(

            ));
        return productOrders;

    }
}