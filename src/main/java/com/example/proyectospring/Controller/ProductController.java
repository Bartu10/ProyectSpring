package com.example.proyectospring.Controller;


import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}/")
    public  ResponseEntity<Object> show(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Object> create(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @DeleteMapping("/products/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> productRepository.delete(value));
        return new ResponseEntity<>(product.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/products/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody Product product) {
        Optional<Product> oldProduct = productRepository.findById(id);
        if(oldProduct.isPresent()) {
            product.setId(id);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
