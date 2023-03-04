package com.example.proyectospring.Repositories;
import com.example.proyectospring.Models.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends CrudRepository <ProductOrder, Long> {}
