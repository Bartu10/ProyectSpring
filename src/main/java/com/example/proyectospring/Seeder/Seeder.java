
package com.example.proyectospring.Seeder;

import com.example.proyectospring.Factories.OrderFactory;
import com.example.proyectospring.Factories.ProductFactory;
import com.example.proyectospring.Factories.ProductOrderFactory;
import com.example.proyectospring.Factories.UserFactory;
import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Models.User;
import com.example.proyectospring.Repositories.OrderRepository;
import com.example.proyectospring.Repositories.ProductOrderRepository;
import com.example.proyectospring.Repositories.ProductRepository;
import com.example.proyectospring.Repositories.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        Faker faker = new Faker();
    User user1 = new User("javi", "bartu", "javi@gmail.com", true ,"123", "64771822806eca2700dc8b2c");
    userRepository.save(user1);
    List<Product> products = productFactory.get(50);
    productRepository.saveAll(products);
    List<Order> orders = orderFactory.get(20, user1);
    orderRepository.saveAll(orders);
    int indiceAleatorio = random.nextInt(orders.size());
    Order elementoAleatorio = orders.get(indiceAleatorio);
        int indiceAleatorio2 = random.nextInt(products.size());
        Product elementoAleatorio2 = products.get(indiceAleatorio2);
    List<ProductOrder> productOrders = productOrderFactory.get(10, orders.get(1), products.get(1));
    productOrderRepository.saveAll(productOrders);


    }


}







