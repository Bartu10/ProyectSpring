package com.example.proyectospring.Controller;
import com.example.proyectospring.Models.Order;
import com.example.proyectospring.Models.ProductOrder;
import com.example.proyectospring.Models.User;
import com.example.proyectospring.Repositories.OrderRepository;
import com.example.proyectospring.Repositories.UserRepository;
import com.example.proyectospring.dto.OrderDto;
import com.example.proyectospring.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/orders/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/")
    public  ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/orders/create")
    public ResponseEntity<Object> create(@RequestBody OrderDto orderDto) {
        Order newOrder=new Order();
        User user = userRepository.findById(orderDto.getUserid()).get();

        newOrder.setUser(user);
        newOrder.setFecha(orderDto.getFecha());
        newOrder.setPrice(orderDto.getPrice());

        orderRepository.save(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }


    @DeleteMapping("/orders/{id}/")
    @PreAuthorize("hasRole('SCOPE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(value -> orderRepository.delete(value));
        return new ResponseEntity<>(order.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/")
    @PreAuthorize("hasRole('SCOPE_ADMIN')")
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

