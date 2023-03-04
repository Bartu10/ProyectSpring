package com.example.proyectospring.Controller;
import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/orders/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/")
    public  ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/orders/create")
    public ResponseEntity<Object> create(@RequestBody Order order) {
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @DeleteMapping("/orders/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(value -> orderRepository.delete(value));
        return new ResponseEntity<>(order.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Order order) {
        Optional<Order> oldOrder = orderRepository.findById(id);
        if(oldOrder.isPresent()) {
            order.setId(id);
            orderRepository.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}

