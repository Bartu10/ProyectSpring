package com.example.proyectospring.Factories;
import com.example.proyectospring.Models.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Component
public class UserFactory {
    Faker esFaker = new Faker();

    public List<User> get(int number) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < number; i++)
            users.add(new User(esFaker.name().firstName(),
                    esFaker.name().username(),
                    esFaker.internet().emailAddress(),
                    esFaker.internet().password(),
                    "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg"));
        return users;

    }
    }
