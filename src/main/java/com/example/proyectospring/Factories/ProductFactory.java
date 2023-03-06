package com.example.proyectospring.Factories;

import com.example.proyectospring.Models.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductFactory {

    Faker esFaker = new Faker(new Locale("es-ES"));

    public List<Product> get(int number) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i <number; i++)
            products.add(new Product(
                    esFaker.name().firstName(),
                    esFaker.team().name(),
                    esFaker.number().numberBetween(1,100),
                    "buen estado"));


        return products;

    }




}


