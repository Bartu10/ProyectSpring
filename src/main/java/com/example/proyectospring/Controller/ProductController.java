package com.example.proyectospring.Controller;


import com.example.proyectospring.Models.Product;
import com.example.proyectospring.Models.User;
import com.example.proyectospring.Repositories.ProductRepository;
import com.example.proyectospring.dto.ProductDto;
import com.example.proyectospring.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public  ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/products/year/{yr}")
    public ResponseEntity<Object> show(@PathVariable("yr") String yr){
        List<Product> product = productRepository.findByYr(yr);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/retro/{retro}")
    public ResponseEntity<Object> show(@PathVariable("retro") Boolean retro ){
        return new ResponseEntity<>(productRepository.findByRetro(retro), HttpStatus.OK);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Object> create(@RequestBody ProductDto product) {
        Product newProduct=new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setRetro(product.getRetro());
        newProduct.setState(product.getState());
        newProduct.setImg(product.getImg());
        newProduct.setYr(product.getYr());
        newProduct.setTeam(product.getTeam());
        newProduct.setXL(product.getXL());
        newProduct.setL(product.getL());
        newProduct.setM(product.getM());
        newProduct.setS(product.getS());
        productRepository.save(newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }




    @DeleteMapping("/products/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> productRepository.delete(value));
        return new ResponseEntity<>(product.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/products/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> oldProduct = productRepository.findById(id);
        if(oldProduct.isPresent()) {
            product.setId(id);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }



}
