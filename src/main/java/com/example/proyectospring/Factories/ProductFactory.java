package com.example.proyectospring.Factories;

import com.example.proyectospring.Models.Product;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Component
public class ProductFactory {

    Faker esFaker = new Faker();
    Lorem lorem = esFaker.lorem();
    public List<Product> get(int number) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i <number; i++)
            products.add(new Product(
                    esFaker.name().firstName(),
                    lorem.paragraph(2),
                    esFaker.number().numberBetween(1,100),
                    "buen estado",
                    "1990",
                    true,
                    "Real Madrid",
                    "https://i.imgur.com/xvgMf2U.jpg",
                    esFaker.number().numberBetween(0,200),
                    esFaker.number().numberBetween(0,200),
                    esFaker.number().numberBetween(0,200),
                    esFaker.number().numberBetween(0,200)



            ));


        return products;

    }




}


