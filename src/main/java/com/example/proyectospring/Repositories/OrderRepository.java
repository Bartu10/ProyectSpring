package com.example.proyectospring.Repositories;


import com.example.proyectospring.Models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {}
