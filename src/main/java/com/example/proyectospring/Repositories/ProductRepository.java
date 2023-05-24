package com.example.proyectospring.Repositories;


import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product>findByYr(String yr);
    List<Product>findByRetro(Boolean retro);
}
