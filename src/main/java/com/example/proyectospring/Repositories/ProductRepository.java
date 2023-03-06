package com.example.proyectospring.Repositories;


import com.example.proyectospring.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {}
