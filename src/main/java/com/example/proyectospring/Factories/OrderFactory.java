package com.example.proyectospring.Factories;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class OrderFactory {

    Faker esFaker = new Faker();
    Random rand = new Random();
    public List<Order> get(int number, User users) {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i <number; i++)
            orders.add(new Order(
                    "10/10/2023", (long) esFaker.number().numberBetween(1,20), users)
            );
        return orders;

    }


}
