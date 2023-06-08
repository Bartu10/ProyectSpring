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

    private Faker faker = new Faker();
    private Lorem lorem = faker.lorem();

    public List<Product> get(int number) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String description = lorem.paragraph(2);
            int price = faker.number().numberBetween(40, 100);
            String state = getRandomState();
            String year = getRandomYear();
            boolean retro = isRetro(year);
            String team = getRandomTeam();
            String name = "Camiseta " + team + ' ' + year + ' ';
            String imageUrl = "https://i.imgur.com/xvgMf2U.jpg"; // No se proporciona la URL de la imagen en este ejemplo
            int XL = faker.number().numberBetween(0, 200);
            int L = faker.number().numberBetween(0, 200);
            int M = faker.number().numberBetween(0, 200);
            int S = faker.number().numberBetween(0, 200);

            Product product = new Product(name, description, price, state, year, retro, team, imageUrl, XL, L, M, S);
            products.add(product);
        }

        return products;
    }

    private String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1970, 2023));
    }

    private boolean isRetro(String year) {
        int yearValue = Integer.parseInt(year);
        return yearValue < 2018;
    }

    private String getRandomState() {
        String[] states = {"Nuevo", "Usado"};
        return states[faker.number().numberBetween(0, states.length)];
    }

    private String getRandomTeam() {
        String[] teams = {
                "Real Madrid", "Barcelona", "Manchester United", "Bayern Munich",
                "Liverpool", "Juventus", "Paris Saint-Germain", "Chelsea",
                "Arsenal", "AC Milan", "Inter Milan", "Borussia Dortmund"
        };
        return teams[faker.number().numberBetween(0, teams.length)];
    }
}







