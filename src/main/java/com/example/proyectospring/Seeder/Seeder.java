package com.example.proyectospring.Seeder;

import com.example.proyectospring.Factories.OrderFactory;
import com.example.proyectospring.Factories.ProductFactory;
import com.example.proyectospring.Factories.ProductOrderFactory;
import com.example.proyectospring.Factories.UserFactory;
import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.User;
import com.example.proyectospring.Repositories.OrderRepository;
import com.example.proyectospring.Repositories.ProductOrderRepository;
import com.example.proyectospring.Repositories.ProductRepository;
import com.example.proyectospring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    ProductFactory productFactory;

    @Autowired
    UserFactory userFactory;

    @Autowired
    OrderFactory orderFactory;

    @Autowired
    ProductOrderFactory productOrderFactory;

    @Override
    public void run(String... args){
    User testUser = new User("javi", "bartu", "javi@gmail.com", "123");
    userRepository.save(testUser);
    List<Product> products = productFactory.get(20);
    productRepository.saveAll(products);




    }


}






