package com.example.proyectospring.Factories;

import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
 @Component
public class OrderFactory {

    Faker esFaker = new Faker(new Locale("es-ES"));

    public List<Order> get(int number) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i <number; i++)
            orders.add(new Order(
                    "10/10/2004", 20L,
                    new User("javi","bartuu","jfue@fije","93ui3")
            ));
        return orders;

    }


}
